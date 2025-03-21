package ink.reactor.world.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PositiveCounterUtil {

    /**
     * Counts the number of unique characters in the given character array using a fixed-size bitmask array.
     * This method assumes that the number of unique characters will not exceed the capacity of the pre-allocated bitmask array.
     *
     * @param values The array of characters to count unique values from.
     * @param defaultDifferentValuesExpected The expected number of unique values. This is used to determine the size of the bitmask array.
     * @return The number of unique characters in the input array.
     * @throws NullPointerException If the input array {@code values} is {@code null}.
     */
    public static int countUnique(final char[] values, final int defaultDifferentValuesExpected) {
        final long[] words = new long[(defaultDifferentValuesExpected / 64) + 1];

        for (final char value : values) {
            words[value >> 6] |= (1L << value);
        }

        int uniqueValues = 0;
        for (final long bitmask : words) {
            uniqueValues += Long.bitCount(bitmask);
        }
        return uniqueValues;
    }

    /**
     * Counts the number of unique characters in the given character array using a dynamically resizable bitmask array.
     * If the input characters exceed the capacity of the initially allocated bitmask array, the array is resized to accommodate the new values.
     *
     * @param values The array of characters to count unique values from.
     * @param defaultDifferentValuesExpected The initial expected number of unique values. This is used to determine the initial size of the bitmask array.
     * @return The number of unique characters in the input array.
     * @throws NullPointerException If the input array {@code values} is {@code null}.
     */
    public static int countUniqueResizable(final char[] values, final int defaultDifferentValuesExpected) {
        long[] words = new long[(defaultDifferentValuesExpected / 64) + 1];

        for (final char value : values) {
            final int index = value >> 6;

            if (index >= words.length) {
                final long[] newWords = new long[index + 1];
                System.arraycopy(words, 0, newWords, 0, words.length);
                words = newWords;
            }
            words[index] |= (1L << value);
        }

        int uniqueValues = 0;
        for (final long bitmask : words) {
            uniqueValues += Long.bitCount(bitmask);
        }
        return uniqueValues;
    }
}
