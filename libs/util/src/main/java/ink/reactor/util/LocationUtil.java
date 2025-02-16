package ink.reactor.util;

public final class LocationUtil {

    private LocationUtil() {
        throw new IllegalAccessError("Can't instance a util class");
    }

    /**
     * Compress x and z cord in one long
     * Support negative numbers
     * Range of cords: -2147483648 -> 2147483647
     *
     * @param x cord x
     * @param z cord z
     * @return compressed key
     */
    public static long compressXZ(final int x, final int z) {
        return ((long) x << 32) | (z & 0xFFFFFFFFL);
    }
}
