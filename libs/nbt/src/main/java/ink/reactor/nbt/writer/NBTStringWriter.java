package ink.reactor.nbt.writer;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.TagNBT;
import ink.reactor.nbt.tags.ByteStringArrayTag;
import ink.reactor.nbt.tags.CompoundTag;
import ink.reactor.nbt.tags.ListTag;
import ink.reactor.nbt.tags.StringTag;
import ink.reactor.util.buffer.writer.WriteBuffer;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class NBTStringWriter {

    public static void writeString(final String string, final WriteBuffer buffer) {
        final byte[] value = string.getBytes(StandardCharsets.UTF_8);
        buffer.writeShort(value.length);
        buffer.writeBytes(value);
    }

    public static void writeString(final byte[] string, final WriteBuffer buffer) {
        buffer.writeShort(string.length);
        buffer.writeBytes(string);    
    }

    public static String toString(final NBT nbt) {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');

        int i = 0;
        for (final TagNBT tag : nbt.getTags()) {
            if (i != 0) {
                sb.append(',');
            }
            sb.append(keyToString(tag.getKey()));
            sb.append(':');
            sb.append(tag.toString());
            i++;
        }
        sb.append('}');
        return sb.toString();
    }

    public static String toJson(final NBT nbt) {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');

        int i = 0;
        for (final TagNBT tag : nbt.getTags()) {
            if (i != 0) {
                sb.append(',');
            }
            sb.append('"');
            sb.append(keyToString(tag.getKey()));
            sb.append('"');
            sb.append(':');

            appendTagToJson(tag, sb);
            i++;
        }
        sb.append('}');
        return sb.toString();
    }

    private static String keyToString(final Object key) {
        if (key == null) {
            return null;
        }
        if (key instanceof byte[] bytes) {
            return new String(bytes);
        }
        return key.toString();
    }

    private static void appendTagToJson(final TagNBT tag, final StringBuilder builder) {
        if (tag instanceof CompoundTag compoundTag) {
            builder.append(toJson(compoundTag.getNbt()));
            return;
        }
        if (tag instanceof ByteStringArrayTag || tag instanceof StringTag) {
            builder.append('"');
            builder.append(tag);
            builder.append('"');            
            return;
        }
        if (!(tag instanceof ListTag listTag)) {
            builder.append(tag);
            return;
        }

        builder.append('[');
        @SuppressWarnings("unchecked")
        final Collection<? extends TagNBT> collection = listTag.getValues();
        for (final TagNBT value : collection) {
            appendTagToJson(value, builder);
        }
        builder.append(']');
    }
}