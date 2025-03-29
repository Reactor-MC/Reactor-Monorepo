package ink.reactor.zlib;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

import ink.reactor.zlib.isal.IGzip;
import ink.reactor.zlib.isal.IGzipConsumer;
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
    public int compress(final byte[] decompressedBytes, final byte[] output) {
        if (deflater == null) {
            return ERROR;
        }
        final IGzipConsumerImpl compressionConsumer = new IGzipConsumerImpl(output);
        IGzip.compress(compressionConsumer, deflater, decompressedBytes, decompressedBytes.length);
        return compressionConsumer.bytes;
    }

    @Override
    public int decompress(final byte[] compressedBytes, final byte[] output) {
        if (inflater == null) {
            return ERROR;
        }
        final IGzipConsumerImpl decompressionConsumer = new IGzipConsumerImpl(output);
        IGzip.decompress(decompressionConsumer, inflater, compressedBytes, output.length);
        return decompressionConsumer.bytes;
    }

    @Override
    public void disposeCompress() throws ZlibException {
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
        private final byte[] buffer;
        private int bytes;

        @Override
        public void accept(ByteBuffer data, int resultCode) {
            if (data == null) {
                bytes = ERROR;
                return;
            }
            bytes = data.capacity();
            data.get(buffer);
        }
    }
}