package ink.reactor.world.chunk;

import ink.reactor.world.chunk.heightmap.ChunkHeightmap;
import ink.reactor.world.chunk.light.LightEngine;
import ink.reactor.world.data.Biome;
import ink.reactor.world.data.DimensionType;

public interface Chunk {
    void clear();

    int getX();
    int getZ();
    int getAmountNegativeSections();

    int getMinY();
    int getMaxY();

    DimensionType getType();

    void setBlock(final int x, int y, final int z, final char blockId);
    char getBlock(final int x, int y, final int z);

    void setBiome(final Biome biome, final int x, final int y, final int z);
    Biome getBiome(final int x, final int y, final int z, Biome defaultBiome);
    default Biome getBiome(final int x, final int y, final int z) {
        return getBiome(x,y,z, Biome.PLAINS);
    }

    char getHighestBlock(final int x, final int z);

    LightEngine getLightEngine();

    ChunkType getChunkType();

    ChunkSection[] getSections();
    ChunkHeightmap getHeightMap();

    default int getAmountBlocks() {
        int nonEmptyBlocks = 0;
        for (final ChunkSection section : getSections()) {
            if (section != null) {
                nonEmptyBlocks += section.getNonEmptyBlocks();
            }
        }
        return nonEmptyBlocks;
    }

    default int getNonEmptySections() {
        int nonEmptySections = 0;
        for (final ChunkSection section : getSections()) {
            if (section != null) {
                nonEmptySections++;
            }
        }
        return nonEmptySections;
    }
}
