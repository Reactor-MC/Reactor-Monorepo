package ink.reactor.world.chunk.vanilla;

import ink.reactor.world.chunk.heightmap.GenericHeightmap;

import java.util.Arrays;

final class VanillaHeightMap extends GenericHeightmap {

    private VanillaHeightMap(VanillaChunkSection[] sections, int minY, int maxY, int worldHeight) {
        super(sections, minY, maxY, worldHeight);
    }

    static VanillaHeightMap of(VanillaChunkSection[] sections, int minY, int maxY, int worldHeight) {
        final VanillaHeightMap heightMap = new VanillaHeightMap(sections, minY, maxY, worldHeight);
        if (minY != 0) {
            Arrays.fill(heightMap.worldSurface, (short)minY);
        }
        return heightMap;
    }

    @Override
    public void findAndSetHighestBlock(final int sectionY, final int x, final int z) {
        final int minY = getMinY();

        for (int i = sectionY; i >= 0; i--) {
            final VanillaChunkSection section = (VanillaChunkSection) sections[i];
            if (section == null || section.isEmpty()) {
                continue;
            }
            final char[] blocks = section.getBlocks();
            for (int y = 15; y >= 0; y--) {
                final char block = blocks[y << 8 | z << 4 | x]; // Manual inlining, but you can use section.getBlockId(x,y,z);
                if (block != 0) {
                    // Transform chunk section y cord to chunk cord, example: 3 to -61
                    worldSurface[z << 4 | x] = (short)( ((i * 16) + y) + minY );
                    return;
                }
            }
        }
        worldSurface[z << 4 | x] = (short)minY; // Can't found any block
    }
}
