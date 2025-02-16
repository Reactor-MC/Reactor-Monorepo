package ink.reactor.protocol.zlib;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.tinylog.Logger;

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
    public int decompress(final byte[] compressedBytes, byte[] output) {
        inflater.setInput(compressedBytes);

        int decompressedSize = 0;

        while (!inflater.finished()) {
            try {
                decompressedSize += inflater.inflate(output);
            } catch (DataFormatException e) {
                Logger.error(e, "Error trying decompress. Using java zlib. Output size: {}. Compressed bytes: {}", output.length, compressedBytes.length);
                return ERROR;
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