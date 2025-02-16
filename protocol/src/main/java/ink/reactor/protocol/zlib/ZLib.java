package ink.reactor.protocol.zlib;

public interface ZLib {

    int ERROR = -1;

    int compress(byte[] decompressedBytes, byte[] output);
    int decompress(byte[] compressedBytes, byte[] output);

    void disposeDecompress();
    void disposeCompress();

    default void dispose() {
        disposeCompress();
        disposeDecompress();
    }
}
