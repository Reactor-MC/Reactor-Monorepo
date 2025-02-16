package ink.reactor.igzip;

import java.io.File;
import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;

import java.lang.invoke.MethodHandle;
import java.net.URISyntaxException;

public class IGzip {

    public static final int DEFAULT_LEVEL = 2;

    private final static MethodHandle
        CREATE_DEFLATE,
        CREATE_INFLATE,
        FREE_DEFLATE,
        FREE_INFLATE,
        COMPRESS,
        DECOMPRESS;

    static {
        final String os = System.getProperty("os.name");

        if (!os.equals("Linux")) {
            throw new RuntimeException("Unsuported OS");
        }

        String igzipPath = "";
        String libIsalPath = "";

        try {
            igzipPath = IGzip.class.getClassLoader().getResource("linux-x86_64/igzip.so").toURI().toString();
            igzipPath = igzipPath.substring(igzipPath.indexOf(':')+1); // remove "file:"

            libIsalPath = IGzip.class.getClassLoader().getResource("linux-x86_64/libisal.so.2.0.31").toURI().toString();
            libIsalPath = igzipPath.substring(igzipPath.indexOf(':')+1); // remove "file:"      
        } catch (final URISyntaxException e) {
            throw new RuntimeException("Failed to load native libraries", e);
        }

        if (!new File(igzipPath).exists()) {
            throw new RuntimeException("Can't found the library igzip.so in .jar. Expected directory: " + igzipPath);
        }
        if (!new File(libIsalPath).exists()) {
            throw new RuntimeException("Can't found the library libisal.so in .jar. Expected directory: " + libIsalPath);
        }

        System.load(libIsalPath);
    
        final SymbolLookup lookup = SymbolLookup.libraryLookup(igzipPath, Arena.global());
        final FunctionDescriptor freeDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS);
    
        CREATE_DEFLATE = createHandle(lookup, "create_deflate", FunctionDescriptor.of(
            ValueLayout.ADDRESS, // isal_zstream*
            ValueLayout.JAVA_INT // compression level
        ));

        CREATE_INFLATE = createHandle(lookup, "create_inflate", FunctionDescriptor.of(ValueLayout.ADDRESS));

        FREE_DEFLATE = createHandle(lookup, "free_deflate", freeDescriptor);
        FREE_INFLATE = createHandle(lookup, "free_inflate", freeDescriptor);

        COMPRESS = createHandle(lookup, "compress", FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // Return compressed buffer length
            ValueLayout.ADDRESS,  // isal_zstream
            ValueLayout.ADDRESS,  // input_array
            ValueLayout.ADDRESS,  // output_array
            ValueLayout.JAVA_INT, // input_array_size
            ValueLayout.JAVA_INT  // output_array_size
        ));

        DECOMPRESS = createHandle(lookup, "decompress", FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,// decompressed buffer length
            ValueLayout.ADDRESS,  // inflate_state
            ValueLayout.ADDRESS,  // input_array
            ValueLayout.ADDRESS,  // output_array
            ValueLayout.JAVA_INT, // input_array_size
            ValueLayout.JAVA_INT  // output_array_size
        ));
    }

    private static MethodHandle createHandle(SymbolLookup lookup, String functionName, FunctionDescriptor descriptor) {
        return Linker.nativeLinker().downcallHandle(
            lookup.find(functionName).orElseThrow(() -> new RuntimeException("Function not found: " + functionName)),
            descriptor
        );
    }

    public static MemorySegment createDeflate(int level) throws Throwable {
        return (MemorySegment)CREATE_DEFLATE.invokeExact(level);
    }

    public static MemorySegment createInflate() throws Throwable {
        return (MemorySegment)CREATE_INFLATE.invoke();
    }

    public static void freeDeflate(final MemorySegment deflate) throws Throwable {
        FREE_DEFLATE.invokeExact(deflate);
    }

    public static void freeInflate(final MemorySegment inflate) throws Throwable {
        FREE_INFLATE.invokeExact(inflate);
    }

    public static void compress(final IGzipConsumer consumer, final MemorySegment deflate, final byte[] inputArray, int outputBufferSize) {
        if (outputBufferSize < inputArray.length + 11) {
            outputBufferSize += 11;
        }

        try (final Arena arena = Arena.ofConfined()) {
            final MemorySegment output = arena.allocate(outputBufferSize);
            final MemorySegment input = arena.allocateArray(ValueLayout.JAVA_BYTE, inputArray);

            final int result = (int) COMPRESS.invokeExact(deflate, input, output, inputArray.length, outputBufferSize);

            if (result < IGzipResult.COMP_OK) {
                consumer.accept(null, result);
                return;
            }

            consumer.accept(output.reinterpret(result).asByteBuffer(), result);
        } catch (final Throwable e) {
            consumer.accept(null, IGzipResult.METHOD_HANDLE_ERROR);
        }
    }

    public static void decompress(final IGzipConsumer consumer, final MemorySegment inflate, final byte[] compressedArray, final int outputBufferSize) {
        try (final Arena arena = Arena.ofConfined()) {
            final MemorySegment output = arena.allocate(outputBufferSize);
            final MemorySegment input = arena.allocateArray(ValueLayout.JAVA_BYTE, compressedArray);

            final long decompressInfo = (long) DECOMPRESS.invokeExact(inflate, input, output, compressedArray.length, outputBufferSize);

            final int result = (int)decompressInfo;
            final int decompressedLength = (int)(decompressInfo >> 32);

            if (result != IGzipResult.ISAL_DECOMP_OK) {
                consumer.accept(null, result);
                return;
            }

            consumer.accept(output.reinterpret(decompressedLength).asByteBuffer(), result);
        } catch (final Throwable e) {
            e.printStackTrace();
            consumer.accept(null, IGzipResult.METHOD_HANDLE_ERROR);
        }
    }
}