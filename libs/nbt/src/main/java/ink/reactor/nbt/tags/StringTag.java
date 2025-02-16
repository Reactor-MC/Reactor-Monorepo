package ink.reactor.nbt.tags;

import java.nio.charset.StandardCharsets;

import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class StringTag extends TagNBT {

    public String value;

    public StringTag(String value, Object key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_STRING;
    }

    @Override
    public void write(final FriendlyBuffer buffer) {
        final byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);
        buffer.tryResize(DataSize.SHORT + valueBytes.length);
        buffer.writeShort(valueBytes.length);
        buffer.writeBytes(valueBytes);
    }

    @Override
    public String toString() {
        return value;
    }
}
