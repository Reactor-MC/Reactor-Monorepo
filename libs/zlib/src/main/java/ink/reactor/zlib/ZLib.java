package ink.reactor.zlib;

public interface ZLib extends AutoCloseable {

    int ERROR = -1;

    byte[] compress(byte[] decompressedBytes) throws Throwable;
    byte[] decompress(final byte[] compressedBytes, final int expectedBufferSize) throws Throwable;

    void disposeDecompress() throws ZlibException;
    void disposeCompress() throws ZlibException;

    @Override
    default void close() throws ZlibException {
        disposeCompress();
        disposeDecompress();
    }
}
