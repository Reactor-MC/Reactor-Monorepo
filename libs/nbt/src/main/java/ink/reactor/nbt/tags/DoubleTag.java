package ink.reactor.nbt.tags;

import ink.reactor.nbt.NumericTag;
import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class DoubleTag extends NumericTag {

    private final double value;

    public DoubleTag(double value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_DOUBLE;
    }

    public byte toByte() {
        return (byte)value;
    }
    public short toShort() {
        return (short)value;
    }
    public float toFloat() {
        return (float)value;
    }
    public double toDouble() {
        return value;
    }
    public int toInt() {
        return (int)value;
    }
    public long toLong() {
        return (long)value;
    }

    @Override
    public void write(final FriendlyBuffer buffer) {
        buffer.writeDouble(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
