package ink.reactor.nbt.writer;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.TagNBT;
import ink.reactor.buffer.writer.DynamicSizeBuffer;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class NBTByteWriter {

    private static DynamicSizeBuffer newBuffer(final NBT nbt) {
        return new DynamicSizeBuffer(nbt.getTags().size() * 32);
    }

    public static byte[] writeTags(final NBT nbt) {
        final DynamicSizeBuffer buffer = newBuffer(nbt);
        nbt.writeTags(buffer);
        return buffer.compress();
    }

    public static byte[] writeNBT(final NBT nbt, final boolean addRootName) {
        final DynamicSizeBuffer buffer = newBuffer(nbt);
        writeNBT(nbt, buffer, addRootName);
        return buffer.compress();
    }

    public static void writeNBT(final NBT nbt, final DynamicSizeBuffer buffer, final boolean addRootName) {
        buffer.writeByte(TagNBT.TAG_COMPOUND);

        if (addRootName) {
            buffer.writeShort(0); // Root name
        }

        nbt.writeTags(buffer);

        buffer.writeByte(TagNBT.TAG_END);
    }

    public static void writeNBT(final NBT nbt, final DynamicSizeBuffer buffer) {
        buffer.writeByte(TagNBT.TAG_COMPOUND);
        nbt.writeTags(buffer);
        buffer.writeByte(TagNBT.TAG_END);
    }

    public static DynamicSizeBuffer writeNBT(final NBT nbt) {
        final DynamicSizeBuffer buffer = newBuffer(nbt);
        writeNBT(nbt, buffer);
        return buffer;
    }
}