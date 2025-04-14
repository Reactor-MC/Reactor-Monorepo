package ink.reactor.nbt.decoder;

import ink.reactor.nbt.TagNBT;
import ink.reactor.nbt.tags.ByteArrayTag;
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
import ink.reactor.nbt.type.NBTGeneral;
import ink.reactor.buffer.reader.ReadBuffer;

import static ink.reactor.nbt.TagNBT.*;

import java.util.ArrayList;

final class NBTDecoder {

    static TagNBT readTag(final ReadBuffer buffer, final byte tagId, final String key) {
        switch (tagId) {
            case TAG_BYTE:
                return new ByteTag(buffer.readByte(), key);
            case TAG_SHORT:
                return new ShortTag(buffer.readShort(), key);
            case TAG_INT:
                return new IntTag(buffer.readInt(), key);
            case TAG_LONG:
                return new LongTag(buffer.readLong(), key);
            case TAG_FLOAT:
                return new FloatTag(buffer.readFloat(), key);
            case TAG_DOUBLE:
                return new DoubleTag(buffer.readDouble(), key);
            case TAG_BYTE_ARRAY:
                return new ByteArrayTag(buffer.readBytes(buffer.readInt()), key);
            case TAG_STRING:
                return new StringTag(readNBTString(buffer), key);
            case TAG_LIST:
                return readList(buffer, key);
            case TAG_COMPOUND:
                return new CompoundTag(readNBT(buffer), key);
            case TAG_INT_ARRAY:
                return new IntArrayTag(readIntArray(buffer), key);
            case TAG_LONG_ARRAY:
                return new LongArrayTag(readLongArray(buffer), key);
        }
        return null;
    }

    static NBTGeneral readNBT(final ReadBuffer buffer) {
        final NBTGeneral nbt = new NBTGeneral();

        while(true) {
            final byte tagId = buffer.readByte();
            if (tagId == TAG_END) {
                return nbt;
            }

            final String key = readNBTString(buffer);
            final TagNBT tagNBT = readTag(buffer, tagId, key);
            if (tagNBT != null) {
                nbt.add(tagNBT);
            }
        }
    }

    static String readNBTString(final ReadBuffer buffer) {
        final int length = buffer.readShort();
        if (length == 0) {
            return "";
        }
        return new String(buffer.readBytes(length));
    }

    private static int[] readIntArray(final ReadBuffer buffer) {
        final int size = buffer.readInt();
        final int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = buffer.readInt();
        }
        return array;
    }

    private static long[] readLongArray(final ReadBuffer buffer) {
        final int size = buffer.readInt();
        final long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = buffer.readLong();
        }
        return array;
    }

    private static ListTag<?> readList(final ReadBuffer buffer, final String key) {
        final byte id = buffer.readByte();
        final int size = buffer.readInt();
        final ArrayList<TagNBT> tags = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            final TagNBT tag = readTag(buffer, id, null);
            if (tag != null) {
                tags.add(tag);
            }
        }

        return new ListTag<>(tags, id, key);
    }
}