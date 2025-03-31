package ink.reactor.dataparser.type.block;

import ink.reactor.fission.field.JavaFieldNames;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BlockParserUtils {

    // Example: if the type name is "test_block" the class name will be "TestBlock"
    public static String toClassName(final String type) {
        final String[] split = type.split(":");
        final String className = JavaFieldNames.toFieldLocalName(split.length == 1 ? split[0] : split[1]);
        return Character.toUpperCase(className.charAt(0)) + className.substring(1);
    }

    public static Class<?> getValueClass(final String type) {
        if (type.indexOf('.') != -1) {
            return double.class;
        }
        if (type.equals("true") || type.equals("false")) {
            return boolean.class;
        }

        try {
            final long value = Long.parseLong(type);
            if (value >= Byte.MAX_VALUE && value <= Byte.MAX_VALUE) {
                return byte.class;
            }
            if (value >= Short.MAX_VALUE && value <= Short.MAX_VALUE) {
                return short.class;
            }
            if (value >= Character.MAX_VALUE && value <= Character.MAX_VALUE) {
                return char.class;
            }
            if (value >= Integer.MAX_VALUE && value <= Integer.MAX_VALUE) {
                return int.class;
            }
            return long.class;
        } catch (NumberFormatException e) {
            return String.class;
        }
    }
}
