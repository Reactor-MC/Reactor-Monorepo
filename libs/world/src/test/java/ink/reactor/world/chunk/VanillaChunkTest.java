package ink.reactor.world.chunk;

import ink.reactor.world.chunk.vanilla.VanillaChunk;
import ink.reactor.world.data.Biome;
import ink.reactor.world.data.DimensionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class VanillaChunkTest {
    private static final char AIR = 0;

    @Test
    public void checkSetBlock() {
        checkSetBlock(newVanillaChunk());
    }

    @Test
    public void checkBiomes() {
        checkBiomes(newVanillaChunk());
    }

    @Test
    public void checkHeightmap() {
        checkHeightmap(newVanillaChunk());
    }

    private void checkSetBlock(final Chunk vanillaChunk) {
        // Set example blocks
        vanillaChunk.setBlock(0, 0, 0, 'a');
        assertEquals('a', vanillaChunk.getBlock(0, 0, 0));
        assertEquals(1, vanillaChunk.getAmountBlocks());

        vanillaChunk.setBlock(1, -64, 1, 'b');
        assertEquals('b', vanillaChunk.getBlock(1, -64, 1));
        assertEquals(2, vanillaChunk.getAmountBlocks());

        vanillaChunk.setBlock(2, 319, 2, 'c');
        assertEquals('c', vanillaChunk.getBlock(2, 319, 2));
        assertEquals(3, vanillaChunk.getAmountBlocks());

        // Set blocks to air
        vanillaChunk.setBlock(1, -64, 1, AIR);
        assertEquals(2, vanillaChunk.getAmountBlocks());

        vanillaChunk.setBlock(2, 319, 2, AIR);
        assertEquals(1, vanillaChunk.getAmountBlocks());

        vanillaChunk.setBlock(0, 0, 0, AIR);
        assertEquals(0, vanillaChunk.getAmountBlocks());

        for (int y = vanillaChunk.getMinY(); y <= vanillaChunk.getMaxY(); y++) {
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x < 16; x++) {
                    vanillaChunk.setBlock(x, y, z, 'a');
                }
            }
        }
        assertEquals(4096 * vanillaChunk.getSections().length, vanillaChunk.getAmountBlocks());

        vanillaChunk.clear();
        assertEquals(0, vanillaChunk.getAmountBlocks());
    }

    private void checkBiomes(final Chunk vanillaChunk) {
        // Create chunk section
        vanillaChunk.setBlock(0, 0, 0, 'a');

        // Even number cords
        vanillaChunk.setBiome(Biome.BADLANDS, 0, 0, 0);
        vanillaChunk.setBiome(Biome.BEACH, 0, 4, 0);
        vanillaChunk.setBiome(Biome.BAMBOO_JUNGLE, 0, 8, 0);
        vanillaChunk.setBiome(Biome.BIRCH_FOREST, 0, 12, 0);

        assertEquals(Biome.BADLANDS, vanillaChunk.getBiome(0, 0, 0));
        assertEquals(Biome.BEACH, vanillaChunk.getBiome(0, 4, 0));
        assertEquals(Biome.BAMBOO_JUNGLE, vanillaChunk.getBiome(0, 8, 0));
        assertEquals(Biome.BIRCH_FOREST, vanillaChunk.getBiome(0, 12, 0));

        // Test non even number cord
        vanillaChunk.setBiome(Biome.DARK_FOREST, 0, 15, 0);
        assertEquals(Biome.DARK_FOREST, vanillaChunk.getBiome(0, 12, 0));

        vanillaChunk.clear();

        for (int y = vanillaChunk.getMinY(); y <= vanillaChunk.getMaxY(); y++) {
            for (int z = 0; z < 16; z+=4) {
                for (int x = 0; x < 16; x+=4) {
                    vanillaChunk.setBiome(Biome.PLAINS, x, y, z);
                    assertEquals(Biome.PLAINS, vanillaChunk.getBiome(x,y,z));
                }
            }
        }
    }

    private void checkHeightmap(final Chunk vanillaChunk) {
        vanillaChunk.setBlock(0, 0, 0, 'a');
        vanillaChunk.setBlock(0, -64, 0, 'b');
        vanillaChunk.setBlock(0, vanillaChunk.getMaxY(), 0, 'c');

        assertEquals('c', vanillaChunk.getHighestBlock(0, 0));
        vanillaChunk.setBlock(0, vanillaChunk.getMaxY(), 0, AIR);

        assertEquals('a', vanillaChunk.getHighestBlock(0, 0));
        vanillaChunk.setBlock(0, 0, 0, AIR);

        assertEquals('b', vanillaChunk.getHighestBlock(0, 0));

        for (int y = vanillaChunk.getMinY(); y <= vanillaChunk.getMaxY(); y++) {
            for (int z = 0; z < 15; z++) {
                for (int x = 0; x < 15; x++) {
                    vanillaChunk.setBlock(x, y, z, 'a');
                    assertEquals(y, vanillaChunk.getHeightMap().getWorldSurface(x, z));
                }
            }
        }
    }

    private static VanillaChunk newVanillaChunk() {
        return VanillaChunk.of(0, 0, DimensionType.OVERWORLD);
    }
}