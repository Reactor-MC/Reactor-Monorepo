package ink.reactor.command.arguments;

import ink.reactor.command.argument.Arguments;
import ink.reactor.command.argument.InvalidArguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

public final class ArgumentsTest {

    @Test
    public void checkArgumentsDefault() {
        final Arguments args = new Arguments(List.of("true", "hi", "5", "2.0", "black"));

        Assertions.assertEquals(5, args.length());
        Assertions.assertTrue(args.getBoolean(0));
        Assertions.assertEquals("hi", args.getString(1));
        Assertions.assertEquals(5, args.getPositiveInt(2));
        Assertions.assertEquals(2.0D, args.getDouble(3, -1));

        final Function<String, Integer> function = (string) -> string.equals("black") ? 1 : 0;
        Assertions.assertEquals(1, args.getCustom(4, function));
    }

    @Test
    public void checkInvalidValues() {
        final Arguments args = new Arguments(List.of("byte", "float", "double", "int", "short"));
        Assertions.assertEquals(InvalidArguments.INVALID_BYTE, args.nextByte());
        Assertions.assertEquals(InvalidArguments.INVALID_FLOAT, args.nextFloat());
        Assertions.assertEquals(InvalidArguments.INVALID_DOUBLE, args.nextDouble());
        Assertions.assertEquals(InvalidArguments.INVALID_INT, args.nextPositiveInt());
        Assertions.assertEquals(InvalidArguments.INVALID_SHORT, args.nextShort());
    }
}