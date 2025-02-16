package ink.reactor.protocol.zlib;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

import org.tinylog.Logger;

import ink.reactor.igzip.IGzip;
import ink.reactor.igzip.IGzipConsumer;
import lombok.RequiredArgsConstructor;

final class IsalZlib implements ZLib {

    private final MemorySegment deflater, inflater;

    private IsalZlib(MemorySegment deflater, MemorySegment inflater) {
        this.deflater = deflater;
        this.inflater = inflater;
    }

    static IsalZlib create() {
        MemorySegment deflater = null, inflater = null;

        try {
            deflater = IGzip.createDeflate(IGzip.DEFAULT_LEVEL);
        } catch (final Throwable e) {
            Logger.error(e, "Error creating igzip deflate");
            return null;
        }

        try {
            inflater = IGzip.createInflate();
        } catch (final Throwable e) {
            Logger.error(e, "Error creating igzip inflate");
            try {
                IGzip.freeDeflate(deflater);
            } catch (Throwable e1) {
                Logger.error(e1, "Error on free deflate");
            }
            return null;
        }

        return new IsalZlib(deflater, inflater);
    }

    @Override
    public int compress(byte[] decompressedBytes, byte[] output) {
        final CompressionConsumer compressionConsumer = new CompressionConsumer(output);
        IGzip.compress(compressionConsumer, deflater, decompressedBytes, decompressedBytes.length);
        return compressionConsumer.compressedBytes;
    }

    @Override
    public int decompress(byte[] compressedBytes, byte[] output) {
        final DecompressionConsumer decompressionConsumer = new DecompressionConsumer(output);
        IGzip.decompress(decompressionConsumer, inflater, compressedBytes, output.length);
        return decompressionConsumer.decompressedBytes;
    }

    @Override
    public void disposeCompress() {
        try {
            IGzip.freeDeflate(deflater);
        } catch (Throwable e) {
            Logger.error(e, "Error on free deflate");
        }
    }

    @Override
    public void disposeDecompress() {
        try {
            IGzip.freeInflate(inflater);
        } catch (Throwable e) {
            Logger.error(e, "Error on free inflate");
        }
    }

    @RequiredArgsConstructor
    private static final class CompressionConsumer implements IGzipConsumer {
        private final byte[] output;
        private int compressedBytes;

        @Override
        public void accept(ByteBuffer data, int resultCode) {
            if (data == null) {
                Logger.error("Error compressing data. Isal error code: ", resultCode);
                compressedBytes = ERROR;
                return;
            }
            compressedBytes = data.capacity();
            data.get(output);
        }
    }

    @RequiredArgsConstructor
    private static final class DecompressionConsumer implements IGzipConsumer {
        private final byte[] output;
        private int decompressedBytes;

        @Override
        public void accept(ByteBuffer data, int resultCode) {
            if (data == null) {
                Logger.error("Error decompressing data. Isal error code: ", resultCode);
                decompressedBytes = ERROR;
                return;
            }
            decompressedBytes = data.capacity();
            data.get(output);
        }
    }
}