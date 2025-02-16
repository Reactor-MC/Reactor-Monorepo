package ink.reactor.util.buffer.writer;

import java.util.UUID;

public interface WriteBuffer {

    void writeVarInt(final int varInt);
    void writeBytes(final byte[] bytes);
    void writeBytes(final byte[] bytes, final int length);
    void writeChars(final char[] chars);
    void writeBoolean(final boolean condition);
    void writeByte(final byte value);
    void writeByte(final int value);
    void writeShort(final int value);
    void writeChar(final char character);
    void writeInt(final int value);
    void writeUUID(final UUID uuid);
    void writeLong(final long value);
    void writeFloat(final float value);
    void writeDouble(final double value);
    void writeString(final String string);

    void revert(final int amountBytes);
    void skip(final int amountBytes);

    byte[] compress();
    int getIndex();
}