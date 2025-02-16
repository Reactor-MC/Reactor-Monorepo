package ink.reactor.util;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class StringUtil {

    private StringUtil() {
        throw new IllegalAccessError("Util class can't be instance");
    }

    /**
     * Split a string by per delimiter character
     *
     * @param text      the string to split
     * @param character delimiter character
     * @return a list of all strings
     */
    public static List<String> split(final String text, final char character) {
        final List<String> list = new LinkedList<>();
        int start = 0;
        int index;
        while ((index = text.indexOf(character, start)) != -1) {
            list.add(text.substring(start, index));
            start = index + 1;
        }
        if (start == 0) {
            list.add(text);
        } else {
            list.add(text.substring(start));
        }
        return list;
    }

    public static String combine(final String... strings) {
        final StringBuilder builder = new StringBuilder();
        for (final String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static int length(final String string) {
        return string == null ? 0 : string.length();
    }
  
    public static boolean isNullOrEmpty(final String string) {
        return string == null || string.isEmpty();
    }

    /**
     * Parses a string to a positive integer. Defaults to -1 if invalid.
     *
     * @param text the string to parse
     * @return the parsed positive integer, or -1 if invalid
     */
    public static int parsePositive(final String text) {
        return parsePositive(text, -1);
    }

    /**
     * Parses a string to a positive integer. Returns a default value if the string is invalid.
     *
     * @param text         the string to parse
     * @param errorDefault the default value to return in case of error
     * @return the parsed positive integer, or the default value if invalid
     */
    public static int parsePositive(final String text, final int errorDefault) {
        if (text.isEmpty()) {
            return errorDefault;
        }
        final int length = text.length();
        if (length == 1) {
            final int value = text.charAt(0) - '0';
            return value < 0 || value > 9 ? errorDefault : value;
        }

        int result = 0;

        for (int i = 0; i < length; i++) {
            final int value = text.charAt(i) - '0';
            if (value < 0 || value > 9) {
                return errorDefault;
            }
            result = (result + value) * 10;
        }
        return result / 10;
    }

    /**
     * Convert a string to uuid.
     *
     * @param input String to convert (with length 16)
     * @return string converted to uuid
     */
    public static UUID stringToUUID(final String input) {
        final byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        final byte[] uuidBytes = new byte[16];

        final int minLength = Math.min(input.length(), 16);
        for (int i = 0; i < minLength; i++) {
            uuidBytes[i] = bytes[i];
        }

        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | (uuidBytes[i] & 0xFF);
        }

        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = (leastSigBits << 8) | (uuidBytes[i] & 0xFF);
        }
        return new UUID(mostSigBits, leastSigBits);
    }
}