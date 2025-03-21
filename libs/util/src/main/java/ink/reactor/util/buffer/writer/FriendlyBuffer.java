package ink.reactor.util.buffer.writer;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.UUID;

import ink.reactor.util.buffer.DataSize;

/*
 * A slow but safe alternative to ExpectedSizeBuffer
 * This alternative don't throw ArrayIndexOutOfBounds if
 * you write more data than the initial size (Automatic resize the buffer) 
 */
public class FriendlyBuffer implements WriteBuffer {

    private ExpectedSizeBuffer currentBuffer;
    private final float resizeFactor;

    public FriendlyBuffer(int initialSize) {
        this.currentBuffer = new ExpectedSizeBuffer(initialSize);
        this.resizeFactor = 1.5f;
    }

    public FriendlyBuffer(int initialSize, float resizeFactor) {
        this.currentBuffer = new ExpectedSizeBuffer(initialSize);
        this.resizeFactor = resizeFactor;
    }

    public final void tryResize(final int amountToAdd) {
        if (currentBuffer.index + amountToAdd >= currentBuffer.buffer.length) {
            final int newSize = (int)((currentBuffer.buffer.length + amountToAdd) * resizeFactor);
            final int currentIndex = currentBuffer.index;

            final byte[] copy = new byte[newSize];
            System.arraycopy(currentBuffer.buffer, 0, copy, 0, currentBuffer.buffer.length);
            this.currentBuffer = new ExpectedSizeBuffer(copy);
            this.currentBuffer.index = currentIndex;
        }
    }

    @Override
    public final void writeVarInt(final int i) {
        tryResize(DataSize.varInt(i));
        currentBuffer.writeVarInt(i);
    }

    @Override
    public final void writeBytes(final byte[] bytes) {
        tryResize(bytes.length);
        currentBuffer.writeBytes(bytes);
    }

    @Override
    public final void writeBytes(final byte[] bytes, final int length) {
        tryResize(length);
        currentBuffer.writeBytes(bytes, length);
    }

    @Override
    public final void writeChars(final char[] chars) {
        tryResize(chars.length * 2);
        currentBuffer.writeChars(chars);
    }

    @Override
    public final void writeBoolean(final boolean v) {
        tryResize(DataSize.BOOLEAN);
        currentBuffer.writeBoolean(v);
    }

    @Override
    public final void writeByte(final byte v) {
        tryResize(DataSize.BYTE);
        currentBuffer.writeByte(v);
    }

    @Override
    public final void writeByte(final int v) {
        tryResize(DataSize.BYTE);
        currentBuffer.writeByte(v);
    }

    @Override
    public final void writeShort(final int v) {
        tryResize(DataSize.SHORT);
        currentBuffer.writeShort(v);
    }

    @Override
    public final void writeChar(final char character) {
        tryResize(DataSize.SHORT);
        currentBuffer.writeChar(character);
    }

    @Override
    public final void writeInt(final int v) {
        tryResize(DataSize.INT);
        currentBuffer.writeInt(v);
    }

    @Override
    public final void writeLong(final long v) {
        tryResize(DataSize.LONG);
        currentBuffer.writeLong(v);
    }

    @Override
    public final void writeFloat(final float v) {
        tryResize(DataSize.FLOAT);
        currentBuffer.writeFloat(v);
    }

    @Override
    public final void writeDouble(final double v) {
        tryResize(DataSize.DOUBLE);
        currentBuffer.writeDouble(v);
    }

    @Override
    public final void writeUUID(final UUID uuid) {
        tryResize(DataSize.UUID);
        currentBuffer.writeUUID(uuid);
    }

    @Override
    public void writeBitSet(final BitSet bitSet) {
        final long[] bitSetArray = bitSet.toLongArray();
        tryResize(DataSize.varInt(bitSetArray.length) + (DataSize.LONG * bitSetArray.length));

        currentBuffer.writeVarInt(bitSetArray.length);
        for (final long value : bitSetArray) {
            currentBuffer.writeLong(value);
        }
    }

    @Override
    public final void writeString(final String string) {
        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        tryResize(bytes.length + DataSize.varInt(bytes.length));
        currentBuffer.writeVarInt(bytes.length);
        currentBuffer.writeBytes(bytes);
    }

    @Override
    public final void revert(final int amountBytes) {
        currentBuffer.revert(amountBytes);
    }

    @Override
    public final void skip(final int amountBytes) {
        currentBuffer.skip(amountBytes);
    }

    @Override
    public final byte[] compress() {
        return currentBuffer.compress();
    }

    @Override
    public final int getIndex() {
        return currentBuffer.getIndex();
    }

    public final ExpectedSizeBuffer getCurrentBuffer() {
        return currentBuffer;
    }

    public final float getResizeFactor() {
        return resizeFactor;
    }

    public final void setCurrentBuffer(ExpectedSizeBuffer currentBuffer) {
        this.currentBuffer = currentBuffer;
    }
}