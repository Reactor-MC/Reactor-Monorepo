package ink.reactor.nbt.decoder;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.TagNBT;
import ink.reactor.util.buffer.reader.ReadBuffer;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class NBTByteDecoder {

    public static NBT decode(final ReadBuffer buffer, final boolean includeRootName) {
        if (buffer.readByte() != TagNBT.TAG_COMPOUND) {
            return null;
        }
        if (includeRootName) {
            NBTDecoder.readNBTString(buffer); // Ignore root name
        }
        return NBTDecoder.readNBT(buffer);
    }
}