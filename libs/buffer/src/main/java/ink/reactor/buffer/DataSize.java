package ink.reactor.buffer;

public final class DataSize {
    public static final int
        UUID = 16,
        LONG = 8,
        BOOLEAN = 1,
        BYTE = 1,
        SHORT = 2,
        INT = 4,
        FLOAT = 4,
        DOUBLE = 8;

    // Remember: Only works for utf8 strings. UTF16 or other can return a fake value
    public static int string(final String string) {
        final int length = string.length();
        return varInt(length) + length;
    }

    public static int prefixedBytes(final byte[] bytes) {
        return varInt(bytes.length) + bytes.length;
    }

    public static int stringArray(final String[] array) {
        int size = varInt(array.length);
        for (final String value : array) {
            size += string(value);
        }
        return size;
    }

    /*
     *  Value       Hex bytes                  Decimal bytes
     *  1           | 0x01                     | 1
     *  127         | 0x7f                     | 127
     *  128         | 0x80 0x01                | 128 1
     *  255         | 0xff 0x01                | 255 1
     *  25565       | 0xdd 0xc7 0x01           | 221 199 1
     *  2097151     | 0xff 0xff 0x7f           | 255 255 127
     *  2147483647  | 0xff 0xff 0xff 0xff 0x07 | 255 255 255 255 7
     *  -1          | 0xff 0xff 0xff 0xff 0x0f | 255 255 255 255 15
     *  -2147483648 | 0x80 0x80 0x80 0x80 0x08 | 128 128 128 128 8
     */
    public static int varInt(final int i) {
        if (i < 0) { // Negative numbers use 5 bytes
            return 5;
        }
        if (i < (1 << 7)) return 1;
        if (i < (1 << 14)) return 2;
        if (i < (1 << 21)) return 3;
        if (i < (1 << 28)) return 4;
        return 5;
    }

    public static int varLong(final long i) {
        if (i < 0) { // Negative numbers use 10 bytes
            return 10;
        }
        if (i < (1 << 7)) return 1;
        if (i < (1 << 14)) return 2;
        if (i < (1 << 21)) return 3;
        if (i < (1 << 28)) return 4;
        if (i < (1 << 35)) return 5;
        if (i < (1 << 42)) return 6;
        if (i < (1 << 49)) return 7;
        if (i < (1 << 56)) return 8;
        if (i < (1 << 63)) return 9;
        return 10;
    }
}