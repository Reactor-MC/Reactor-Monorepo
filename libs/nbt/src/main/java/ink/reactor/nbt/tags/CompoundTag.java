package ink.reactor.nbt.tags;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

public final class CompoundTag extends TagNBT {

    private final NBT nbt;

    public CompoundTag(NBT nbt, Object key) {
        super(key);
        this.nbt = nbt;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_COMPOUND;
    }

    @Override
    public void write(final FriendlyBuffer buffer) {
        nbt.writeTags(buffer);
        buffer.writeByte(TAG_END);
    }

    public NBT getNbt() {
        return nbt;
    }

    @Override
    public String toString() {
        return nbt.toString();
    }
}