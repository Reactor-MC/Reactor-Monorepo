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
}
