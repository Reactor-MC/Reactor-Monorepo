package ink.reactor.nbt.type;

import java.util.Collection;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.TagNBT;
import ink.reactor.nbt.collection.NBTCollection;
import ink.reactor.nbt.writer.NBTStringWriter;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

/*
 * Nbt specialized in fast add and iteration
 * 
 * Don't recommended for get, remove or addSet operations
 */
public final class NBTFastAdd implements NBT {
    
    private final NBTCollection tags;

    public NBTFastAdd() {
        this.tags = new NBTCollection();
    }

    public NBTFastAdd(int defaultSize) {
        this.tags = new NBTCollection(defaultSize, 6);
    }

    public NBTFastAdd(final NBTCollection collection) {
        this.tags = collection;
    }

    @Override
    public TagNBT get(final Object key) {
        return tags.get(key);
    }

    @Override
    public void add(final TagNBT nbt) {
        tags.add(nbt);
    }

    @Override
    public void writeTags(final FriendlyBuffer buffer) {
        final TagNBT[] tags = this.tags.getArray(); // Don't create an iterator object = Fast iteration
        for (final TagNBT tag : tags) {
            if (tag != null) {
                buffer.writeByte(tag.getId());
                NBTKeyWriter.writeKey(buffer, tag.getKey());
                tag.write(buffer);
            }
        }
    }

    @Override
    public boolean addOrSet(final TagNBT nbt) {
        return tags.addOrSet(nbt);
    }

    @Override
    public TagNBT remove(final Object key) {
        return tags.removeKey(key);
    }

    @Override
    public Collection<TagNBT> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return NBTStringWriter.toString(this);
    }


    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof NBTFastAdd nbtFastAdd && nbtFastAdd.tags.equals(this.tags));
    }
}
