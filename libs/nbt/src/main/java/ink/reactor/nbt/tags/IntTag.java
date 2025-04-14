package ink.reactor.nbt.tags;

import ink.reactor.nbt.NumericTag;
import ink.reactor.nbt.TagNBT;
import ink.reactor.buffer.writer.DynamicSizeBuffer;

public final class IntTag extends NumericTag {

    private final int value;

    public IntTag(int value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_INT;
    }

    public byte toByte() {
        return (byte)value;
    }
    public short toShort() {
        return (short)value;
    }
    public float toFloat() {
        return value;
    }
    public double toDouble() {
        return value;
    }
    public int toInt() {
        return value;
    }
    public long toLong() {
        return value;
    }

    @Override
    public void write(final DynamicSizeBuffer buffer) {
        buffer.writeInt(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
