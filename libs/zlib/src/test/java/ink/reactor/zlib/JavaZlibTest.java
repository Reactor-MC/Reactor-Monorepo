package ink.reactor.zlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaZlibTest {

    private static final String INPUT_STRING = "Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @Test
    public void checkJavaZlib() throws Throwable {
        try(final JavaZlib javaZlib = new JavaZlib()) {

            final byte[] inputBytes = INPUT_STRING.getBytes();
            final byte[] compressedBytes = javaZlib.compress(inputBytes);
            final byte[] decompressedBytes = javaZlib.decompress(compressedBytes, inputBytes.length);

            Assertions.assertEquals(INPUT_STRING.length(), decompressedBytes.length);
            Assertions.assertEquals(INPUT_STRING, new String(decompressedBytes));
        }
    }

    @Test
    public void checkIGZIP() throws Throwable {
        try(final IsalZlib isalZlib = IsalZlib.create()) {
            final byte[] inputBytes = INPUT_STRING.getBytes();

            final byte[] compressedBytes = isalZlib.compress(inputBytes);
            Assertions.assertNotNull(compressedBytes);

            final byte[] decompressedBytes = isalZlib.decompress(compressedBytes, inputBytes.length);
            Assertions.assertNotNull(decompressedBytes);

            Assertions.assertEquals(INPUT_STRING.length(), decompressedBytes.length);
            Assertions.assertEquals(INPUT_STRING, new String(decompressedBytes));
        }
    }
}