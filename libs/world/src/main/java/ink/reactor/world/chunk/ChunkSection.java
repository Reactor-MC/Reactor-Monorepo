package ink.reactor.world.chunk;

import ink.reactor.world.data.Biome;

public interface ChunkSection {
    void setBlockId(final int x, final int y, final int z, final char id);
    char getBlockId(final int x, final int y, final int z);

    void setBiome(final byte biome, final int x, final int y, final int z);
    byte getBiomeId(final int x, final int y, final int z);

    default void setBiome(final Biome biome, final int x, final int y, final int z) {
        setBiome((byte)biome.id(), x, y, z);
    }
    default Biome getBiome(final int x, final int y, final int z) {
        return Biome.ALL.get(getBiomeId(x,y,z));
    }

    Chunk getChunk();

    int getNonEmptyBlocks();

    boolean isEmpty();
}