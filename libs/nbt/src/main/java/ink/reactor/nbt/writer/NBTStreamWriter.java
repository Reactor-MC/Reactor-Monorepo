package ink.reactor.nbt.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.compression.CompressionType;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class NBTStreamWriter {

    public static void writeFile(final Path path, final NBT nbt) throws IOException {
        writeFile(path, nbt, null);
    }

    public static void writeFile(final Path path, final NBT nbt, final CompressionType compression) throws IOException {
        try (final OutputStream outputStream = Files.newOutputStream(path)) {
            writeStream(outputStream, nbt, compression);
        }
    }

    public static void writeStream(final OutputStream outputStream, final NBT nbt, final CompressionType compression) throws IOException {
        final byte[] data = NBTByteWriter.writeNBT(nbt, true);
        if (compression == null) {
            outputStream.write(data);
            return;
        }

        switch (compression) {
            case NONE:
                outputStream.write(data);
                break;
            case GZIP:
                writeGzip(data, outputStream);
                break;
            case ZLIB:
                writeZlib(data, outputStream);
                break;
        }
    }

    public static void writeGzip(final byte[] data, final OutputStream outputStream) throws IOException {
        try (final GZIPOutputStream gzipOs = new GZIPOutputStream(outputStream)) {
            gzipOs.write(data);
        }
    }

    public static void writeZlib(final byte[] data, final OutputStream outputStream) throws IOException {
        final Deflater deflater = new Deflater();
        try {
            deflater.setInput(data);
            deflater.finish();

            final byte[] buffer = new byte[Math.min(1024, data.length)];

            while (!deflater.finished()) {
                final int compressedSize = deflater.deflate(buffer);
                outputStream.write(buffer, 0, compressedSize);
            }
        } finally {
            deflater.end();
        }
    }
}