package ink.reactor.item.component;

import ink.reactor.nbt.TagNBT;
import ink.reactor.buffer.writer.DynamicSizeBuffer;

final class Basic {

    static boolean serializeInt(final DynamicSizeBuffer buffer, final Object component) {
        if (component instanceof Number number) {
            buffer.writeVarInt(number.intValue());
            return true;
        }
        return false;
    }

    static void serializeBoolean(final DynamicSizeBuffer buffer, final Object component) {
        buffer.writeBoolean((component instanceof Boolean booleanValue) ? booleanValue : false);
    }

    static void serializeString(final DynamicSizeBuffer buffer, final Object component) {
        buffer.writeString(component.toString());
    }

    static void serializeEmptyNBT(final DynamicSizeBuffer buffer, final Object component) {
        buffer.writeByte(TagNBT.TAG_COMPOUND);
        buffer.writeByte(TagNBT.TAG_END);
    }

    static boolean serializeEnum(final DynamicSizeBuffer buffer, final Object component) {
        if (component instanceof Enum enumObject) {
            buffer.writeVarInt(enumObject.ordinal());
            return true;
        }
        return false;
    }

}
