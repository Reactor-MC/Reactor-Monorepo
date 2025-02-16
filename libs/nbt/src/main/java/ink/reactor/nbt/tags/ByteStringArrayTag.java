package ink.reactor.nbt.tags;

import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class ByteStringArrayTag extends TagNBT {

    public final byte[] value;

    public ByteStringArrayTag(byte[] value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_STRING;
    }

    @Override
    public void write(final FriendlyBuffer buffer) {
        buffer.tryResize(DataSize.SHORT + value.length);
        buffer.getCurrentBuffer().writeShort(value.length);
        buffer.getCurrentBuffer().writeBytes(value);
    }

    @Override
    public String toString() {
        return new String(value);
    }
}