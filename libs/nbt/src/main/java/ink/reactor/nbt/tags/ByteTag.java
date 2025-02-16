package ink.reactor.nbt.tags;

import ink.reactor.nbt.NumericTag;
import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class ByteTag extends NumericTag {

    private final byte value;

    public ByteTag(byte value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_BYTE;
    }

    public byte toByte() {
        return value;
    }
    public short toShort() {
        return value;
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
    public void write(final FriendlyBuffer buffer) {
        buffer.writeByte(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
