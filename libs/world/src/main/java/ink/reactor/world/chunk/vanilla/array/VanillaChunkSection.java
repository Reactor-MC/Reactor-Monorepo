package ink.reactor.world.chunk.vanilla.array;

import ink.reactor.util.buffer.writer.WriteBuffer;
import ink.reactor.world.chunk.ChunkSection;
import ink.reactor.world.chunk.light.LightHolder;
import ink.reactor.world.data.Biome;
import ink.reactor.world.util.PositiveCounterUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

/*
 * A chunk section is a cuboid of 16*16*16 blocks (4096 total)
 * This sections contains biomes and lights
 */
@Getter
@RequiredArgsConstructor
public final class VanillaChunkSection implements ChunkSection {

    private static final int AIR = 0;

    // Entries are stored in order of increasing x coordinate,
    // within rows at increasing z coordinates, within layers at increasing y coordinates.
    // In other words, if the Data Array were a multidimensional array, it would be indexed array[y][z][x].
    private final char[] blocks = new char[4096];

    // Consists of 64 entries, representing 4x4x4 biomes regions in the chunk section.
    private final byte[] biomes = new byte[64];

    private final LightHolder lightHolder;
    private final VanillaChunk chunk;

    private int nonEmptyBlocks = 0;

    @Override
    public char getBlockId(final int x, final int y, final int z) {
        return blocks[y << 8 | z << 4 | x];
    }

    @Override
    public void setBlockId(final int x, final int y, final int z, final char id) {
        final int key = y << 8 | z << 4 | x;
        final char old = blocks[key];
        blocks[key] = id;

        if (id != AIR) {
            if (old == AIR) {
                ++nonEmptyBlocks;
            }
            return;
        }
        if (old != AIR) {
            --nonEmptyBlocks;
        }
    }

    // Formula of biomes: ((y / 4) << 4) | ((z / 4) << 2) | (x / 4)
    // Explication:
    //      A Chunk section contains 16 blocks in x, z and y cords.
    //      Biomes are stored in a cuboid of 4x4x4. 2 bits can represent 4 different values.
    //      How many biomes cuboids are in a chunk section? Exact, 4. (16 blocks / 4)
    @Override
    public void setBiome(final byte biome, final int x, final int y, final int z) {
        biomes[((y >> 2) << 4) | ((z >> 2) << 2) | (x >> 2)] = biome;
    }

    @Override
    public byte getBiomeId(final int x, final int y, final int z) {
        return biomes[((y >> 2) << 4) | ((z >> 2) << 2) | (x >> 2)];
    }

    @Override
    public boolean isEmpty() {
        return nonEmptyBlocks == 0;
    }

    public void serializeBlocks(WriteBuffer buffer) {

        int blockBPE = calculateBitsPerEntry(); // Método para calcular bits per entry de bloques
        buffer.writeByte(blockBPE);

        if (blockBPE == 0) {
            buffer.writeVarInt(blocks[0]); // Un solo valor para todos los bloques
        } else {
            serializeBlockPalette(buffer, blockBPE);
        }
    }

    public void serializeBiome(WriteBuffer buffer) {
        int biomeBPE = getBiomeBitsPerEntry(Biome.ALL.size());
        buffer.writeByte(biomeBPE);

        if (biomeBPE == 0) {
            buffer.writeVarInt(biomes[0]);
        } else {
            serializeBiomePalette(buffer, biomeBPE);
        }
    }

    public int calculateBitsPerEntry() {
        final int uniqueBlocks = PositiveCounterUtil.countUnique(blocks, Character.MAX_VALUE);
        final int bitsPerEntry = (int) Math.ceil(Math.log(uniqueBlocks) / Math.log(2));

        if (bitsPerEntry < 4) return 4; // Indirect
        else if (bitsPerEntry > 8 && uniqueBlocks > 256) return 13; // Direct

        return bitsPerEntry;
    }

    private void serializeBlockPalette(WriteBuffer buffer, int bitsPerEntry) {
        Map<Character, Integer> palette = new HashMap<>();
        List<Integer> dataArray = new ArrayList<>();

        int index = 0;
        for (char block : blocks) {
            palette.putIfAbsent(block, index++);
            dataArray.add(palette.get(block));
        }

        buffer.writeVarInt(palette.size());
        for (char block : palette.keySet()) {
            buffer.writeVarInt(block);
        }
        serializeDataArray(buffer, dataArray, bitsPerEntry);
    }

    private void serializeBiomePalette(WriteBuffer buffer, int bitsPerEntry) {
        Map<Byte, Integer> palette = new HashMap<>();
        List<Integer> dataArray = new ArrayList<>();

        int index = 0;
        for (byte biome : biomes) {
            palette.putIfAbsent(biome, index++);
            dataArray.add(palette.get(biome));
        }

        buffer.writeVarInt(palette.size());
        for (byte biome : palette.keySet()) {
            buffer.writeVarInt(biome);
        }
        serializeDataArray(buffer, dataArray, bitsPerEntry);
    }

    private void serializeDataArray(WriteBuffer buffer, List<Integer> data, int bitsPerEntry) {
        int entriesPerLong = 64 / bitsPerEntry;
        int arraySize = (int) Math.ceil(data.size() / (double) entriesPerLong);
        long[] packedData = new long[arraySize];

        int longIndex = 0, bitOffset = 0;
        for (int value : data) {
            packedData[longIndex] |= ((long) value & ((1L << bitsPerEntry) - 1)) << bitOffset;
            bitOffset += bitsPerEntry;
            if (bitOffset + bitsPerEntry > 64) {
                longIndex++;
                bitOffset = 0;
            }
        }

        buffer.writeVarInt(packedData.length);
        for (long value : packedData) {
            buffer.writeLong(value);
        }
    }

    private int getBiomeBitsPerEntry(int biomeRegistrySize) {
        return (int) Math.ceil(Math.log(biomeRegistrySize) / Math.log(2));
    }
}