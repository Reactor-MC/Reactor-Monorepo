package ink.reactor.nbt.tags;

import java.util.Collection;

import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class ListTag<T extends TagNBT> extends TagNBT {

    private final Collection<T> values;
    private final byte listId;

    public ListTag(Collection<T> values, byte listId, Object key) {
        super(key);
        this.listId = listId;
        this.values = values;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_LIST;
    }

    @Override
    public void write(final FriendlyBuffer buffer) {
        buffer.writeByte(listId);
        buffer.writeInt(values.size());
        for (final TagNBT tag : values) {
            tag.write(buffer);
        }
    }

    @Override
    public String toString() {
        return values.toString();
    }

    public Collection<T> getValues() {
        return values;
    }

    public byte getListId() {
        return listId;
    }
}
