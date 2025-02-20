package ink.reactor.nbt.writer;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class NBTByteWriter {

    private static FriendlyBuffer newBuffer(final NBT nbt) {
        return new FriendlyBuffer(nbt.getTags().size() * 16, 32);
    }

    public static byte[] writeTags(final NBT nbt) {
        final FriendlyBuffer buffer = newBuffer(nbt);
        nbt.writeTags(buffer);
        return buffer.compress();
    }

    public static byte[] writeNBT(final NBT nbt, final boolean addRootName) {
        final FriendlyBuffer buffer = newBuffer(nbt);
        writeNBT(nbt, buffer, addRootName);
        return buffer.compress();
    }

    public static void writeNBT(final NBT nbt, final FriendlyBuffer buffer, final boolean addRootName) {  
        buffer.writeByte(TagNBT.TAG_COMPOUND);

        if (addRootName) {
            buffer.writeShort(0); // Root name
        }

        nbt.writeTags(buffer);

        buffer.writeByte(TagNBT.TAG_END);
    }

    public static void writeNBT(final NBT nbt, final FriendlyBuffer buffer) {  
        buffer.writeByte(TagNBT.TAG_COMPOUND);
        nbt.writeTags(buffer);
        buffer.writeByte(TagNBT.TAG_END);
    }
}