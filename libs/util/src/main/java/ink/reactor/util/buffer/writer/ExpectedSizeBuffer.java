package ink.reactor.util.buffer.writer;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.UUID;

/*
 * Unsafe but faster buffer for write data.
 * If you have known exactly what is the size of the buffer,
 * use this alternative to improve performance
 */
public final class ExpectedSizeBuffer implements WriteBuffer {

    public static final byte[] EMPTY_BUFFER = new byte[0];

    public final byte[] buffer;
    public int index = 0;

    public ExpectedSizeBuffer(int initialSize) {
        this.buffer = new byte[initialSize];
    }

    public ExpectedSizeBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public ExpectedSizeBuffer(byte[] buffer, int index) {
        this.buffer = buffer;
        this.index = index;
    }

    @Override
    public void writeVarInt(int value) {
        if (value < 0) {
            buffer[index++] = (byte)(value & 0x7F | 0x80);
            buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
            buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
            buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
            buffer[index++] = (byte)(value >>> 7);
            return;
        }
        if (value < (1 << 7)) {
            buffer[index++] = (byte)value;
            return;
        }
        if (value < (1 << 14)) {
            buffer[index++] = (byte)(value & 0x7F | 0x80);
            buffer[index++] = (byte)(value >>> 7);
            return;
        } 
        if (value < (1 << 21)) {
            buffer[index++] = (byte)(value & 0x7F | 0x80);
            buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
            buffer[index++] = (byte)(value >>> 7);
            return;
        }
        if (value < (1 << 28)) {
            buffer[index++] = (byte)(value & 0x7F | 0x80);
            buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
            buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
            buffer[index++] = (byte)(value >>> 7);
            return;
        }
        buffer[index++] = (byte)(value & 0x7F | 0x80);
        buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
        buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
        buffer[index++] = (byte)((value >>>= 7) & 0x7F | 0x80);
        buffer[index++] = (byte)(value >>> 7);
    }

    @Override
    public void writeBytes(byte[] bytes) {
        System.arraycopy(bytes, 0, buffer, index, bytes.length);
        index += bytes.length;
    }

    @Override
    public void writeBytes(byte[] bytes, int length) {
        System.arraycopy(bytes, 0, buffer, index, length);
        index += length;
    }

    @Override
    public void writeChars(char[] chars) {
        int x = index;
        for (final char v : chars) {
            buffer[x++] = (byte)(v >>> 8);
            buffer[x++] = (byte)(v);
        }
        index += chars.length * 2;
    }

    @Override
    public void writeBoolean(boolean condition) {
        buffer[index++] = condition ? (byte)1 : 0;
    }

    @Override
    public void writeByte(byte value) {
        buffer[index++] = value;
    }

    @Override
    public void writeByte(int value) {
        buffer[index++] = (byte)value;
    }

    @Override
    public void writeShort(int value) {
        buffer[index++] = (byte)(value >>> 8);
        buffer[index++] = (byte)(value);
    }

    @Override
    public void writeChar(char character) {
        buffer[index++] = (byte)(character >>> 8);
        buffer[index++] = (byte)(character);
    }

    @Override
    public void writeInt(int value) {
        buffer[index++] = (byte)(value >>> 24);
        buffer[index++] = (byte)(value >>> 16);
        buffer[index++] = (byte)(value >>> 8);
        buffer[index++] = (byte)(value);
    }

    @Override
    public void writeLong(long value) {
        buffer[index++] = (byte)(value >>> 56);
        buffer[index++] = (byte)(value >>> 48);
        buffer[index++] = (byte)(value >>> 40);
        buffer[index++] = (byte)(value >>> 32);
        buffer[index++] = (byte)(value >>> 24);
        buffer[index++] = (byte)(value >>> 16);
        buffer[index++] = (byte)(value >>> 8);
        buffer[index++] = (byte)(value);
    }

    @Override
    public void writeFloat(float value) {
        final int i = Float.floatToIntBits(value);
        buffer[index++] = (byte) (i >> 24);
        buffer[index++] = (byte) (i >> 16);
        buffer[index++] = (byte) (i >> 8);
        buffer[index++] = (byte) (i);
    }

    @Override
    public void writeDouble(double value) {
        final long l = Double.doubleToLongBits(value);
        buffer[index++] = (byte) (l >> 56);
        buffer[index++] = (byte) (l >> 48);
        buffer[index++] = (byte) (l >> 40);
        buffer[index++] = (byte) (l >> 32);
        buffer[index++] = (byte) (l >> 24);
        buffer[index++] = (byte) (l >> 16);
        buffer[index++] = (byte) (l >> 8);
        buffer[index++] = (byte) (l);
    }

    @Override
    public void writeUUID(UUID uuid) {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
    }

    @Override
    public void writeBitSet(final BitSet bitSet) {
        final long[] bitSetArray = bitSet.toLongArray();
        writeVarInt(bitSetArray.length);
        for (final long value : bitSetArray) {
            writeLong(value);
        }
    }

    @Override
    public void writeString(String string) {
        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > 32767) {
            throw new IllegalStateException("String too big (was " + string.length() + " bytes encoded, max " + 32767 + ")");
        }
        writeVarInt(bytes.length);
        System.arraycopy(bytes, 0, buffer, index, bytes.length);
        index += bytes.length;
    }

    @Override
    public void revert(final int amountBytes) {
        final int bytesToRevert = index-amountBytes;
        if (bytesToRevert < 0) {
            throw new IllegalArgumentException("Amount to revert need be less than bufferSize+1");
        }
        for (int i = index; i > bytesToRevert; i--) {
            buffer[i] = 0;
            index--;
        }
    }

    @Override
    public void skip(final int amountBytes) {
        if (this.index + amountBytes >= buffer.length) {
            throw new ArrayIndexOutOfBoundsException("Max buffer length: " + buffer.length + ". Current index: " + this.index + ". Expected index: " + (this.index + amountBytes));
        }
        this.index += amountBytes;
    }

    @Override
    public byte[] compress() {
        if (buffer.length == index) {
            return buffer;
        }
        final byte[] compressedBuffer = new byte[index];
        System.arraycopy(buffer, 0, compressedBuffer, 0, index);
        return compressedBuffer;
    }

    @Override
    public int getIndex() {
        return index;
    }
}