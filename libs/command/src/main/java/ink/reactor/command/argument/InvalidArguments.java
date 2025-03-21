package ink.reactor.command.argument;

import lombok.experimental.UtilityClass;

/**
 * Default invalid return on parse a string
 * @see ink.reactor.command.argument.Arguments
 */
@UtilityClass
public final class InvalidArguments {
    public static final long INVALID_LONG = Long.MIN_VALUE;
    public static final int INVALID_INT = Integer.MIN_VALUE;
    public static final short INVALID_SHORT = Short.MIN_VALUE;
    public static final byte INVALID_BYTE = Byte.MIN_VALUE;
    public static final double INVALID_DOUBLE = Double.NaN;
    public static final float INVALID_FLOAT = Float.NaN;
}
