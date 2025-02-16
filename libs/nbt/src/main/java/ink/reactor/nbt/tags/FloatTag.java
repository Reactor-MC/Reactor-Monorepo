package ink.reactor.nbt.tags;

import ink.reactor.nbt.NumericTag;
import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class FloatTag extends NumericTag {

    private final float value;

    public FloatTag(float value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_FLOAT;
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
        return (int)value;
    }
    public long toLong() {
        return (long)value;
    }

    @Override
    public void write(final FriendlyBuffer buffer) {
        buffer.writeFloat(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
