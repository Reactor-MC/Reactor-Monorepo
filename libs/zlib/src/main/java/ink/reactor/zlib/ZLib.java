package ink.reactor.zlib;

public interface ZLib {

    int ERROR = -1;

    int compress(byte[] decompressedBytes, byte[] output);
    int decompress(byte[] compressedBytes, byte[] output) throws ZlibException;

    void disposeDecompress() throws ZlibException;
    void disposeCompress() throws ZlibException;

    default void dispose() throws ZlibException {
        disposeCompress();
        disposeDecompress();
    }
}
