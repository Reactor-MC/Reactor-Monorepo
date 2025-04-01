package ink.reactor.zlib;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class JavaZlib implements ZLib {

    private final Deflater deflater;
    private final Inflater inflater;

    public JavaZlib(int deflaterLevel) {
        deflater = new Deflater(deflaterLevel);
        inflater = new Inflater();
    }

    public JavaZlib() {
        this(Deflater.DEFAULT_COMPRESSION);
    }

    @Override
    public byte[] compress(final byte[] decompressedBytes) {
        deflater.setInput(decompressedBytes);
        deflater.finish();

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(decompressedBytes.length);
        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        deflater.reset();
        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompress(final byte[] compressedBytes, final int expectedBufferSize) throws Throwable {
        inflater.setInput(compressedBytes);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(expectedBufferSize);
        byte[] buffer = new byte[1024];

        while (!inflater.finished()) {
            outputStream.write(buffer, 0, inflater.inflate(buffer));
        }

        inflater.reset();
        return outputStream.toByteArray();
    }

    @Override
    public void disposeCompress() {
        deflater.end();
    }

    @Override
    public void disposeDecompress() {
        inflater.end();
    }
}