package ink.reactor.world.chunk.heightmap;

import ink.reactor.nbt.NBT;

public interface ChunkHeightmap {
    short[] getHeights();
    short getWorldSurface(final int x, final int z);
    void setSurface(final char blockId, final int x, final int y, final int sectionY, final int z);

    NBT serialize();
}