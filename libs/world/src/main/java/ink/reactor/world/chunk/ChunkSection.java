package ink.reactor.world.chunk;

import ink.reactor.world.data.Biome;

public interface ChunkSection {
    char getBlock(final int x, final int y, final int z);
    void setBlock(final int x, final int y, final int z, final char id);

    byte getBiomeId(final int x, final int y, final int z);
    void setBiome(final byte biome, final int x, final int y, final int z);

    default void setBiome(final Biome biome, final int x, final int y, final int z) {
        setBiome((byte)biome.id(), x, y, z);
    }
    default Biome getBiome(final int x, final int y, final int z) {
        return Biome.ALL.get(getBiomeId(x,y,z));
    }

    Chunk getChunk();

    int getNonEmptyBlocks();

    boolean isEmpty();
    boolean isLoaded();
}