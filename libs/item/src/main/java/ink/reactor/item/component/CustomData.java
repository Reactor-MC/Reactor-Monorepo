package ink.reactor.item.component;

import ink.reactor.item.data.CustomModelData;
import ink.reactor.nbt.NBT;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

final class CustomData {

    static boolean serializeNBT(final FriendlyBuffer buffer, final Object component) {
        if (component instanceof NBT nbt) {
            NBTByteWriter.writeNBT(nbt, buffer);
            return true;
        }
        return false;
    }

    static boolean serializeModelData(final FriendlyBuffer buffer, final Object component) {
        if (!(component instanceof CustomModelData customModelData)) {
            return false;
        }

        buffer.writeVarInt(customModelData.floats().length);
        for (final float value : customModelData.floats()) {
            buffer.writeFloat(value);
        }

        buffer.writeVarInt(customModelData.flags().length);
        for (final boolean value : customModelData.flags()) {
            buffer.writeBoolean(value);
        }

        buffer.writeVarInt(customModelData.strings().length);
        for (final String value : customModelData.strings()) {
            buffer.writeString(value);
        }

        buffer.writeVarInt(customModelData.colors().length);
        for (final int value : customModelData.colors()) {
            buffer.writeInt(value);
        }
        return true;
    } 
}
