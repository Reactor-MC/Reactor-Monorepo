package ink.reactor.nbt.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ink.reactor.nbt.NBT;
import ink.reactor.nbt.decoder.NBTByteDecoder;
import ink.reactor.nbt.type.NBTGeneral;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.util.buffer.reader.BufferReader;

public class EncoderDecoderTest {

    @Test
    public void compareEqual() {
        final NBTGeneral nbtGeneral = new NBTGeneral();
        loadNBT(nbtGeneral);

        final byte[] buffer = NBTByteWriter.writeNBT(nbtGeneral, false);
        final NBT decodedNBT = NBTByteDecoder.decode(new BufferReader(buffer), false);

        Assertions.assertEquals(nbtGeneral, decodedNBT);
    }

    private void loadNBT(final NBT nbt) {
        nbt.addString("string", "value1");
        nbt.addByte("byte", (byte)1);
        nbt.addShort("short", (short)2);
        nbt.addInt("int", 3);
        nbt.addLong("long", 4L);
        nbt.addByteArray("byteArray", new byte[] {0, 1, 2, 3});
        nbt.addIntArray("intArray", new int[] {0, 1, 2, 3});
        nbt.addLongArray("longArray", new long[] {0, 1, 2, 3});
        nbt.addFloat("float", 5F);
        nbt.addDouble("double", 6F);
        nbt.addStringList("stringList", List.of("s1", "s2", "s3"));

        final NBTGeneral extra = new NBTGeneral();
        extra.addString("extra-key", "extra-value");

        nbt.addCompound("extra", extra);
    }
}
