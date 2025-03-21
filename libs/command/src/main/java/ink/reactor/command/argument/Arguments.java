package ink.reactor.command.argument;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ink.reactor.command.parser.IntegerParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public final class Arguments {
    private final List<String> args;
    private int next = 0;

    private String getArg(final int arg) {
        return (arg >= 0 && arg < args.size()) ? args.get(arg) : null;
    }

    public boolean isEmpty() {
        return args.isEmpty();
    }

    public int length() {
        return args.size();
    }

    public void skip(final int count) {
        next += count;
    }

    public int getNextAndIncrease() {
        return next++;
    }

    public boolean nextBoolean() {
        return Boolean.parseBoolean(nextString());
    }

    public String nextString() {
        return getArg(next++);
    }

    public String nextString(final String defaultReturn) {
        final String string = nextString();
        return string == null ? defaultReturn : string;
    }

    public List<String> nextStrings(final int count) {
        final List<String> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            values.add(nextString());
        }
        return values;
    }

    public int nextInt() {
        return getInt(next++);
    }

    public int nextInt(final int defaultReturn) {
        return getInt(next++, defaultReturn);
    }

    public Integer nextInteger() {
        return getInteger(next++);
    }

    public long nextLong() {
        return getLong(next++);
    }

    public BigInteger nextBigInteger() {
        return getBigInteger(next++);
    }

    public BigDecimal nextBigDecimal() {
        return getBigDecimal(next++);
    }

    public double nextDouble() {
        return getDouble(next++, InvalidArguments.INVALID_DOUBLE);
    }

    public float nextFloat() {
        return getFloat(next++, InvalidArguments.INVALID_FLOAT);
    }

    public int nextPositiveInt() {
        return getPositiveInt(next++);
    }

    public byte nextByte() {
        return getByte(next++);
    }

    public short nextShort() {
        return getShort(next++);
    }

    public String getCommand() {
        return args.getFirst();
    }

    public String getString(final int index) {
        return getArg(index);
    }

    public String getString(final int index, final String defaultReturn) {
        final String string = getArg(index);
        return string == null ? defaultReturn : string;
    }

    public <T> T getCustom(final int index, final Function<String, T> function) {
        return function.apply(getArg(index));
    }

    public boolean getBoolean(final int index) {
        return Boolean.parseBoolean(args.get(index));
    }

    public short getShort(final int index) {
        return getShort(index, InvalidArguments.INVALID_SHORT);
    }

    public byte getByte(final int index) {
        return getByte(index, InvalidArguments.INVALID_BYTE);
    }

    public short getShort(final int index, final short defaultReturn) {
        final int value = IntegerParser.parse(getArg(index), defaultReturn);
        return (value < Short.MIN_VALUE || value > Short.MAX_VALUE) ? defaultReturn : (short) value;
    }

    public byte getByte(final int index, final byte defaultReturn) {
        final int value = IntegerParser.parse(getArg(index), defaultReturn);
        return (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) ? defaultReturn : (byte) value;
    }

    public int getInt(final int index) {
        return getInt(index, InvalidArguments.INVALID_INT);
    }

    public Integer getInteger(final int index) {
        try {
            return Integer.parseInt(getString(index));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public int getPositiveInt(final int index) {
        return getPositiveInt(index, InvalidArguments.INVALID_INT);
    }

    public int getInt(final int index, final int defaultReturn) {
        return IntegerParser.parse(getArg(index), defaultReturn);
    }

    public int getIntInRange(final int index, final int min, final int max, final int defaultReturn) {
        final int value = getInt(index, defaultReturn);
        return (value < min || value > max) ? defaultReturn : value;
    }

    public int getPositiveInt(final int index, final int defaultReturn) {
        return IntegerParser.parsePositive(getArg(index), defaultReturn);
    }

    public int getIntFromBinary(final int index, final int defaultReturn) {
        return IntegerParser.parseBinary(getArg(index), defaultReturn);
    }

    public int getIntFromHex(final int index, final int defaultReturn) {
        return IntegerParser.parseHex(getArg(index), defaultReturn);
    }

    public int getNegativeInt(final int index, final int defaultReturn) {
        return IntegerParser.parseNegative(getArg(index), defaultReturn);
    }

    public <T extends Enum<T>> T getEnum(final int index, Class<T> enumClass, T defaultReturn) {
        final String arg = getArg(index);
        if (arg == null) {
            return defaultReturn;
        }
        try {
            return Enum.valueOf(enumClass, arg.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return defaultReturn;
        }
    }

    public long getLong(final int index) {
        return getLong(index, InvalidArguments.INVALID_LONG);
    }

    public long getLong(final int index, final long defaultReturn) {
        final String arg = getArg(index);
        if (arg == null) {
            return defaultReturn;
        }
        try {
            return Long.parseLong(arg);
        } catch (NumberFormatException e) {
            return defaultReturn;
        }
    }

    public BigInteger getBigInteger(final int index) {
        final String arg = getArg(index);
        if (arg == null) {
            return null;
        }
        try {
            return new BigInteger(arg);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public BigDecimal getBigDecimal(final int index) {
        final String arg = getArg(index);
        if (arg == null) {
            return null;
        }
        try {
            return new BigDecimal(arg);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public double getDouble(final int index, final double defaultReturn) {
        final String string = getArg(index);
        if (string == null) {
            return defaultReturn;
        }
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return defaultReturn;
        }
    }

    public float getFloat(final int index, final float defaultReturn) {
        final String string = getArg(index);
        if (string == null) {
            return defaultReturn;
        }
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            return defaultReturn;
        }
    }
}