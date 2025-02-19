package ink.reactor.zlib;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

import ink.reactor.zlib.isal.IGzip;
import ink.reactor.zlib.isal.IGzipConsumer;
import lombok.RequiredArgsConstructor;

final class IsalZlib implements ZLib {

    private final MemorySegment deflater, inflater;

    private IsalZlib(MemorySegment deflater, MemorySegment inflater) {
        this.deflater = deflater;
        this.inflater = inflater;
    }

    static IsalZlib create() throws ZlibException {
        MemorySegment deflater = null, inflater = null;

        try {
            deflater = IGzip.createDeflate(IGzip.DEFAULT_LEVEL);
        } catch (final Throwable e) {
            throw new ZlibException("Error creating igzip deflate", e);
        }

        try {
            inflater = IGzip.createInflate();
        } catch (final Throwable e) {
            try {
                IGzip.freeDeflate(deflater);
            } catch (Throwable e1) {
                throw new ZlibException("Error creating igzip deflate", e);
            }
            throw new ZlibException("Error creating igzip inflate", e);
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
    public void disposeCompress() throws ZlibException {
        try {
            IGzip.freeDeflate(deflater);
        } catch (Throwable e) {
            throw new ZlibException("Error on free deflate", e);
        }
    }

    @Override
    public void disposeDecompress() throws ZlibException {
        try {
            IGzip.freeInflate(inflater);
        } catch (Throwable e) {
            throw new ZlibException("Error on free inflate", e);
        }
    }

    @RequiredArgsConstructor
    private static final class CompressionConsumer implements IGzipConsumer {
        private final byte[] output;
        private int compressedBytes;

        @Override
        public void accept(ByteBuffer data, int resultCode) {
            if (data == null) {
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
                decompressedBytes = ERROR;
                return;
            }
            decompressedBytes = data.capacity();
            data.get(output);
        }
    }
}