package ink.reactor.util;

public final class MathUtil {

    private MathUtil() {
        throw new IllegalAccessError("Can't instance a util class");
    }

    public static int positiveCeilDiv(final int a, final int b) {
        return -Math.floorDiv(-a, b);
    }

    public static int floor(final double a) {
        final int b = (int) a;
        return a < b ? b - 1 : b;
    }
}