package ink.reactor.nbt;

import ink.reactor.nbt.tags.CompoundTag;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public abstract class TagNBT {

    public static TagNBT END_TAG = new CompoundTag(null, null);

    public static final byte
        TAG_END = 0,
        TAG_BYTE = 1,
        TAG_SHORT = 2,
        TAG_INT = 3,
        TAG_LONG = 4,
        TAG_FLOAT = 5,
        TAG_DOUBLE = 6,
        TAG_BYTE_ARRAY = 7,
        TAG_STRING = 8,
        TAG_LIST = 9,
        TAG_COMPOUND = 10,
        TAG_INT_ARRAY = 11,
        TAG_LONG_ARRAY = 12;

    private final Object key;

    public TagNBT(Object key) {
        this.key = key;
    }

    public abstract byte getId();
    public abstract void write(final FriendlyBuffer buffer);

    @Override
    public final boolean equals(final Object obj) {
        return obj == this || (obj instanceof TagNBT tagNBT)
            && tagNBT.getId() == this.getId()
            && tagNBT.key.equals(this.key);
    }

    public Object getKey() {
        return key;
    }
}
