package ink.reactor.nbt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import ink.reactor.nbt.tags.ByteArrayTag;
import ink.reactor.nbt.tags.ByteStringArrayTag;
import ink.reactor.nbt.tags.ByteTag;
import ink.reactor.nbt.tags.CompoundTag;
import ink.reactor.nbt.tags.DoubleTag;
import ink.reactor.nbt.tags.FloatTag;
import ink.reactor.nbt.tags.IntArrayTag;
import ink.reactor.nbt.tags.IntTag;
import ink.reactor.nbt.tags.ListTag;
import ink.reactor.nbt.tags.LongArrayTag;
import ink.reactor.nbt.tags.LongTag;
import ink.reactor.nbt.tags.ShortTag;
import ink.reactor.nbt.tags.StringTag;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public interface NBT {

    TagNBT get(final Object key);
    void add(final TagNBT nbt);

    /**
     * Adds or sets a tag in the NBT structure.
     *
     * @param nbt The tag to add or set.
     * @return {@code true} if the tag was set (replaced), or {@code false} if added.
     */
    boolean addOrSet(final TagNBT nbt);

    TagNBT remove(final Object key);
    void writeTags(final FriendlyBuffer buffer);

    Collection<TagNBT> getTags();

    default byte getType(final Object key) {
        final TagNBT tag = get(key);
        return tag == null ? 0 : tag.getId();
    }

    default byte getByte(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toByte() : 0;
    }

    default short getShort(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toShort() : 0;
    }

    default int getInt(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toInt() : 0;
    }

    default float getFloat(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toFloat() : 0F;
    }

    default double getDouble(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toDouble() : 0D;
    }

    default String getString(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof StringTag stringTag ? stringTag.value : null;
    }

    default byte[] getByteArray(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof ByteArrayTag byteArrayTag ? byteArrayTag.value : null;
    }

    default int[] getIntArray(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof IntArrayTag intArrayTag ? intArrayTag.value : null;
    }

    default long[] getLongArray(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof LongArrayTag longArrayTag ? longArrayTag.value : null;
    }

    default CompoundTag getCompound(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof CompoundTag compoundTag ? compoundTag : null;
    }

    default ListTag<?> getList(final Object key) {
        final TagNBT nbt = get(key);
        return nbt instanceof ListTag<?> listTag ? listTag : null;
    }

    default void addByte(final Object key, final byte value) {
        add(new ByteTag(value, key));
    }

    default void addBoolean(final Object key, final boolean value) {
        add(new ByteTag((value) ? (byte)1 : 0, key));
    }

    default void addShort(final Object key, final short value) {
        add(new ShortTag(value, key));
    }

    default void addLong(final Object key, final long value) {
        add(new LongTag(value, key));
    }

    default void addInt(final Object key, final int value) {
        add(new IntTag(value, key));
    }

    default void addFloat(final Object key, final float value) {
        add(new FloatTag(value, key));
    }

    default void addDouble(final Object key, final double value) {
        add(new DoubleTag(value, key));
    }

    default void addIntArray(final Object key, final int[] value){
        add(new IntArrayTag(value, key));
    }

    default void addString(final Object key, final String value) {
        add(new StringTag(value, key));
    }

    default void addString(final Object key, final byte[] value) {
        add(new ByteStringArrayTag(value, key));
    }

    default void addByteArray(final Object key, final byte[] value) {
        add(new ByteArrayTag(value, key));
    }

    default void addLongArray(final Object key, final long[] value) {
        add(new LongArrayTag(value, key));
    }

    default void addCompound(final Object key, final NBT nbt) {
        if (nbt != this) {
            add(new CompoundTag(nbt, key));
        }
    }

    default void addStringList(final Object key, final Collection<String> values) {
        final List<StringTag> collection = new ArrayList<>(values.size());
        for (final String value : values) {
            collection.add(new StringTag(value, null));
        }
        add(new ListTag<StringTag>(collection, TagNBT.TAG_STRING, key));
    }

    default void addUUID(final Object key, final UUID uuid) {
        final long mostSig = uuid.getMostSignificantBits();
        final long leastSig = uuid.getLeastSignificantBits();

        final int int1 = (int) (mostSig >>> 32);
        final int int2 = (int) mostSig;
        final int int3 = (int) (leastSig >>> 32);
        final int int4 = (int) leastSig;

        addIntArray(key, new int[] {int1, int2, int3, int4});
    }

    default <T extends TagNBT> void addList(final String key, final List<T> list, final byte listId) {
        add(new ListTag<T>(list, listId, key));
    }
}