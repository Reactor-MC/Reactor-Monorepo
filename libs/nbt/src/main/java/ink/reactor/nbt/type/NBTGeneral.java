package ink.reactor.nbt.type;

import java.util.Collection;
import java.util.Map;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.TagNBT;
import ink.reactor.nbt.writer.NBTStringWriter;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

/*
 * A nbt for general proposes (add, read, iterate, remove, etc)
 */
public final class NBTGeneral implements NBT {

    private final Map<Object, TagNBT> tags = new Object2ObjectOpenHashMap<>();

    @Override
    public TagNBT get(final Object key) {
        return tags.get(key);
    }

    @Override
    public void add(final TagNBT nbt) {
        tags.put(nbt.getKey(), nbt);
    }

    @Override
    public boolean addOrSet(final TagNBT nbt) {
        return tags.put(nbt.getKey(), nbt) != null;
    }

    @Override
    public TagNBT remove(final Object key) {
        return tags.remove(key);
    }

    @Override
    public void writeTags(final FriendlyBuffer buffer) {
        final Collection<TagNBT> tags = this.tags.values();
        for (final TagNBT tag : tags) {
            if (tag != null) {
                buffer.writeByte(tag.getId());
                NBTKeyWriter.writeKey(buffer, tag.getKey());
                tag.write(buffer);
            }
        }
    }

    @Override
    public Collection<TagNBT> getTags() {
        return tags.values();
    }

    @Override
    public String toString() {
        return NBTStringWriter.toString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof NBTGeneral nbtGeneral && nbtGeneral.tags.equals(this.tags));
    }
}