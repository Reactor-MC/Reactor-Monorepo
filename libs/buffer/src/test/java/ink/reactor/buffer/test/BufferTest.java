package ink.reactor.buffer.test;

import ink.reactor.buffer.reader.BufferReader;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import ink.reactor.buffer.writer.DynamicSizeBuffer;
import ink.reactor.buffer.writer.WriteBuffer;

import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BufferTest {

    @Test
    public void testPrimitives() {
        testPrimitives(newFriendlyBuffer());
        testPrimitives(new ExpectedSizeBuffer(512));
    }

    @Test
    public void testObjects() {
        testObjects(newFriendlyBuffer());
        testObjects(new ExpectedSizeBuffer(512));
    }

    private void testPrimitives(final WriteBuffer writer) {
        writer.writeBoolean(true);
        writer.writeChar('a');

        writer.writeByte(61);
        writer.writeShort(-512);
        writer.writeInt(312);
        writer.writeLong(3423);
        writer.writeDouble(34.12D);
        writer.writeFloat(12.61F);
        writer.writeVarInt(-5435);

        final BufferReader reader = new BufferReader(writer.compress());
        assertTrue(reader.readBoolean());
        assertEquals('a', reader.readChar());
        assertEquals(61, reader.readByte());
        assertEquals(-512, reader.readShort());
        assertEquals(312, reader.readInt());
        assertEquals(3423, reader.readLong());
        assertEquals(34.12D, reader.readDouble());
        assertEquals(12.61F, reader.readFloat());
        assertEquals(-5435, reader.readVarInt());
    }

    private void testObjects(final WriteBuffer writer) {
        final long[] longArray = new long[] { 21, 63, 21 ,43, 65432, 2};
        final UUID uuid = UUID.randomUUID();
        final String string = "testString";
        final BitSet bitSet = new BitSet(12);
        bitSet.set(1);
        bitSet.set(3);
        bitSet.set(5);

        writer.writeLongArray(longArray);
        writer.writeBytes(string.getBytes());
        writer.writeString(string);
        writer.writeUUID(uuid);
        writer.writeBitSet(bitSet);

        final BufferReader reader = new BufferReader(writer.compress());
        assertArrayEquals(longArray, reader.readLongArray());
        assertArrayEquals(string.getBytes(), reader.readBytes(string.length()));
        assertEquals(string, reader.readString());
        assertEquals(uuid, reader.readUUID());
        assertEquals(bitSet, reader.readBitSet());
    }

    private DynamicSizeBuffer newFriendlyBuffer() {
        return new DynamicSizeBuffer(512);
    }
}
