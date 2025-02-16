package ink.reactor.nbt.tags;

import java.util.Arrays;

import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class ByteArrayTag extends TagNBT {

    public final byte[] value;

    public ByteArrayTag(byte[] value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_BYTE_ARRAY;
    }

    @Override
    public void write(final FriendlyBuffer buffer) {
        buffer.tryResize(value.length + DataSize.INT);
        buffer.getCurrentBuffer().writeInt(value.length);
        buffer.getCurrentBuffer().writeBytes(value);
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}
