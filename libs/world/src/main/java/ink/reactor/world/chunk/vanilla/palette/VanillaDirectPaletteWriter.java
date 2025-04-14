package ink.reactor.world.chunk.vanilla.palette;

import ink.reactor.buffer.writer.WriteBuffer;

public class VanillaDirectPaletteWriter {

    public static final int CHUNK_BPE = 15; // BPE = Bits per Entry
    public static final int BIOME_BPE = 6;

    public static void writeBlocks(final char[] blocks, final WriteBuffer buffer) {
        final int valuesPerLong = 64 / CHUNK_BPE;
        final long[] packedArray = new long[((blocks.length + valuesPerLong - 1) / valuesPerLong)];

        int i = 0;
        for (final char block : blocks) {
            final int bitIndex = (i % valuesPerLong) * CHUNK_BPE;
            final int longIndex = i / valuesPerLong;
            packedArray[longIndex] |= ((long) block & ((1L << CHUNK_BPE) - 1)) << bitIndex;
            i++;
        }

        buffer.writeLongArray(packedArray);
    }

    public static void writeBiomes(final byte[] biomes, final WriteBuffer buffer) {
        final int valuesPerLong = 64 / BIOME_BPE;
        final long[] packedArray = new long[(biomes.length + valuesPerLong - 1) / valuesPerLong];

        int i = 0;
        for (final byte biome : biomes) {
            final int bitIndex = (i % valuesPerLong) * BIOME_BPE;
            final int longIndex = i / valuesPerLong;
            packedArray[longIndex] |= ((long) biome & ((1L << BIOME_BPE) - 1)) << bitIndex;
            i++;
        }

        buffer.writeLongArray(packedArray);
    }
}
