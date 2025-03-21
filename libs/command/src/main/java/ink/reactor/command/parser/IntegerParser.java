package ink.reactor.command.parser;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class IntegerParser {

    public static int parse(final String text, final int defaultReturn) {
        if (text == null || text.isEmpty()) {
            return defaultReturn;
        }

        final int length = text.length();
        int i = 0;

        final char firstCharacter = text.charAt(0);
        int multiplier = 1;

        if (firstCharacter == '+') {
            i = 1;
        } else if (firstCharacter == '-') {
            multiplier = -1;
            i = 1;
        }

        int result = 0;

        while (i < length) {
            final int number = text.charAt(i) - '0';
            if (number < 0 || number > 9) {
                return defaultReturn;
            }
            result += number;
            result *= 10;
            i++;
        }

        return multiplier * (result / 10);
    }

    public static int parseHex(final String hex, final int defaultReturn) {
        if (hex == null || hex.isEmpty()) {
            return defaultReturn;
        }

        final int length = hex.length();
        int result = 0;

        for (int i = 0; i < length; i++) {
            final char character = hex.charAt(i);
            final int value;

            switch (hex.charAt(i)) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    value = (character - '0');
                    break;
                case 'a', 'b', 'c', 'd', 'f':
                    value = ((character - 'a') + 10);
                    break;
                case 'A', 'B', 'C', 'D', 'F':
                    value = ((character - 'A') + 10);
                    break;
                default:
                    return defaultReturn;
            }
            result = (result << 4) | value;
        }
        return result;
    }

    public static int parseBinary(final String binary, final int defaultReturn){
        if (binary == null) {
            return defaultReturn;
        }

        int result = 0;
        final int length = binary.length();
 
        for (int i = 0; i < length; i++) {
            final char bit = binary.charAt(i);
            if (bit == '0') {
                result <<= 1;
            } else if (bit == '1') {
                result = (result << 1) | 1;
            } else {
                return defaultReturn;
            }
        }
        return result;
    }

    public static int parsePositive(final String text, final int defaultReturn) {
        final int value = parse(text, defaultReturn);
        return value < 0 ? defaultReturn : value;
    }

    public static int parseNegative(final String text, final int defaultReturn) {
        final int value = parse(text, defaultReturn);
        return value > 0 ? defaultReturn : value;
    }
}