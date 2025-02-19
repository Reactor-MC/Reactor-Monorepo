package ink.reactor.zlib;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

final class JavaZlib implements ZLib {

    private final Deflater deflater = new Deflater();
    private final Inflater inflater = new Inflater();

    @Override
    public int compress(final byte[] decompressedBytes, final byte[] output) {
        deflater.setInput(decompressedBytes);
        deflater.finish();

        int compressedSize = 0;

        while (!deflater.finished()) {
            compressedSize += deflater.deflate(output);
        }

        deflater.reset();
        return compressedSize;
    }

    @Override
    public int decompress(final byte[] compressedBytes, byte[] output) throws ZlibException {
        inflater.setInput(compressedBytes);

        int decompressedSize = 0;

        while (!inflater.finished()) {
            try {
                decompressedSize += inflater.inflate(output);
            } catch (DataFormatException e) {
                throw new ZlibException("Error trying decompress. Using java zlib. Output size: " + output.length + ". Compressed bytes: " + compressedBytes.length, e);
            }
        }
        inflater.reset();
        return decompressedSize;
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