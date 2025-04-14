package ink.reactor.world.chunk.vanilla;

import ink.reactor.world.chunk.ChunkSection;
import ink.reactor.world.chunk.light.LightHolder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VanillaChunkSection implements ChunkSection {

    private static final int AIR = 0;

    // Entries are stored in order of increasing x coordinate,
    // within rows at increasing z coordinates, within layers at increasing y coordinates.
    // In other words, if the Data Array were a multidimensional array, it would be indexed array[y][z][x].
    private char[] blocks = new char[4096];

    // Consists of 64 entries, representing 4x4x4 biomes regions in the chunk section.
    private byte[] biomes = new byte[64];

    private LightHolder lightHolder;
    private VanillaChunk chunk;

    private int nonEmptyBlocks = 0;

    @Override
    public char getBlock(final int x, final int y, final int z) {
        return blocks[y << 8 | z << 4 | x];
    }

    @Override
    public void setBlock(final int x, final int y, final int z, final char id) {
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
    public byte getBiomeId(final int x, final int y, final int z) {
        return biomes[((y >> 2) << 4) | ((z >> 2) << 2) | (x >> 2)];
    }

    @Override
    public void setBiome(final byte biome, final int x, final int y, final int z) {
        biomes[((y >> 2) << 4) | ((z >> 2) << 2) | (x >> 2)] = biome;
    }

    @Override
    public boolean isEmpty() {
        return nonEmptyBlocks <= 0;
    }

    @Override
    public boolean isLoaded() {
        return nonEmptyBlocks != -1;
    }
}
