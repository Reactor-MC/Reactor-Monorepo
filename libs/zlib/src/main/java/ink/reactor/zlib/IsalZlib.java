package ink.reactor.zlib;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

import ink.reactor.zlib.igzip.IGzip;
import ink.reactor.zlib.igzip.IGzipConsumer;
import lombok.RequiredArgsConstructor;

final class IsalZlib implements ZLib {

    private MemorySegment deflater, inflater;

    private IsalZlib(MemorySegment deflater, MemorySegment inflater) {
        this.deflater = deflater;
        this.inflater = inflater;
    }

    static IsalZlib create() throws ZlibException {
        MemorySegment deflater, inflater;

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
                throw new ZlibException("Error on free igzip deflate", e);
            }
            throw new ZlibException("Error creating igzip inflate", e);
        }

        return new IsalZlib(deflater, inflater);
    }

    @Override
    public byte[] compress(final byte[] decompressedBytes) throws Throwable {
        if (deflater == null) {
            return null;
        }
        final IGzipConsumerImpl compressionConsumer = new IGzipConsumerImpl();
        IGzip.compress(compressionConsumer, deflater, decompressedBytes, decompressedBytes.length);

        if (compressionConsumer.buffer == null) {
            throw new ZlibException("Error on compress. Error code: " + compressionConsumer.resultCode + ". Decompressed Buffer size: " + decompressedBytes.length);
        }
        return compressionConsumer.buffer;
    }

    @Override
    public byte[] decompress(final byte[] compressedBytes, final int expectedBufferSize) throws Throwable {
        if (inflater == null) {
            return null;
        }
        final IGzipConsumerImpl decompressionConsumer = new IGzipConsumerImpl();
        IGzip.decompress(decompressionConsumer, inflater, compressedBytes, expectedBufferSize);

        if (decompressionConsumer.buffer == null) {
            throw new ZlibException("Error on decompress. Error code: " + decompressionConsumer.resultCode + ". Expected Buffer size: " + expectedBufferSize + " Compressed buffer size: " + compressedBytes.length);
        }
        return decompressionConsumer.buffer;
    }

    @Override
    public void close() throws ZlibException {
        disposeCompress();
        disposeDecompress();
    }

    @Override
    public void disposeCompress() throws ZlibException {
        if (deflater == null) {
            return;
        }
        try {
            IGzip.freeDeflate(deflater);
        } catch (Throwable e) {
            throw new ZlibException("Error on free deflate", e);
        } finally {
            deflater = null;
        }
    }

    @Override
    public void disposeDecompress() throws ZlibException {
        if (inflater == null) {
            return;
        }
        try {
            IGzip.freeInflate(inflater);
        } catch (Throwable e) {
            throw new ZlibException("Error on free inflate", e);
        } finally {
            inflater = null;
        }
    }

    @RequiredArgsConstructor
    private static final class IGzipConsumerImpl implements IGzipConsumer {
        private byte[] buffer;
        private int resultCode;
        @Override
        public void accept(ByteBuffer data, int resultCode) {
            this.resultCode = resultCode;
            if (data != null) {
                this.buffer = new byte[data.capacity()];
                data.get(buffer);
            }
        }
    }
}