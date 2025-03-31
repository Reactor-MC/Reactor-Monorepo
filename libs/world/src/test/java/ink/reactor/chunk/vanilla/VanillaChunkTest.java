package ink.reactor.chunk.vanilla;

import ink.reactor.util.buffer.writer.FriendlyBuffer;
import ink.reactor.world.chunk.Chunk;
import ink.reactor.world.chunk.vanilla.array.VanillaChunk;
import ink.reactor.world.chunk.vanilla.palette.VanillaPaletteChunk;
import ink.reactor.world.data.Biome;

import ink.reactor.world.data.DimensionType;
import ink.reactor.world.palette.PaletteWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public final class VanillaChunkTest {

    private static final char AIR = 0;

    @Test
    public void checkDefaultValues() {
        checkDefaultValues(newVanillaChunk());
        checkDefaultValues(newVanillaPaletteChunk());
    }

    @Test
    public void checkSetBlock() {
        checkSetBlock(newVanillaChunk());
        checkSetBlock(newVanillaPaletteChunk());
    }

    @Test
    public void checkBiomes() {
        checkBiomes(newVanillaChunk());
        checkBiomes(newVanillaPaletteChunk());
    }

    @Test
    public void checkHeightmap() {
        checkHeightmap(newVanillaChunk());
    }

    private static void checkDefaultValues(final Chunk vanillaChunk) {
        Assertions.assertEquals(0, vanillaChunk.getAmountBlocks());
        Assertions.assertEquals(1, vanillaChunk.getX());
        Assertions.assertEquals(2, vanillaChunk.getZ());

        for (final int height : vanillaChunk.getHeightMap().getHeights()) {
            Assertions.assertEquals(DimensionType.OVERWORLD.minY(), height);
        }
        Assertions.assertEquals(4, vanillaChunk.getAmountNegativeSections());
    }

    private static void checkSetBlock(final Chunk vanillaChunk) {
        vanillaChunk.setBlock(0, 0, 0, 'a');
        vanillaChunk.setBlock(1, -64, 1, 'b');
        vanillaChunk.setBlock(2, 319, 2, 'c');

        Assertions.assertEquals('a', vanillaChunk.getBlock(0, 0, 0));
        Assertions.assertEquals('b', vanillaChunk.getBlock(1, -64, 1));
        Assertions.assertEquals('c', vanillaChunk.getBlock(2, 319, 2));
    }

    private static void checkHeightmap(final Chunk vanillaChunk) {
        vanillaChunk.setBlock(0, 0, 0, 'a');
        vanillaChunk.setBlock(0, -64, 0, 'b');
        vanillaChunk.setBlock(0, 319, 0, 'c');

        Assertions.assertEquals('c', vanillaChunk.getHighestBlock(0, 0));
        vanillaChunk.setBlock(0, 319, 0, AIR);

        Assertions.assertEquals('a', vanillaChunk.getHighestBlock(0, 0));
        vanillaChunk.setBlock(0, 0, 0, AIR);

        Assertions.assertEquals('b', vanillaChunk.getHighestBlock(0, 0));

        int y = vanillaChunk.getMaxY();
        for (int z = 0; z < 15; z++) {
            for (int x = 0; x < 15; x++) {
                vanillaChunk.setBlock(x,y--,z,'a');
            }
        }

        y = vanillaChunk.getMaxY();
        for (int z = 0; z < 15; z++) {
            for (int x = 0; x < 15; x++) {
                Assertions.assertEquals(y--, vanillaChunk.getHeightMap().getWorldSurface(x,z));
            }
        }
    }

    private static void checkBiomes(final Chunk vanillaChunk) {
        // Create chunk section
        vanillaChunk.setBlock(0, 0, 0, 'a');

        // Even number cords
        vanillaChunk.setBiome(Biome.BADLANDS, 0, 0, 0);
        vanillaChunk.setBiome(Biome.BEACH, 0, 4, 0);
        vanillaChunk.setBiome(Biome.BAMBOO_JUNGLE, 0, 8, 0);
        vanillaChunk.setBiome(Biome.BIRCH_FOREST, 0, 12, 0);

        Assertions.assertEquals(Biome.BADLANDS, vanillaChunk.getBiome(0, 0, 0));
        Assertions.assertEquals(Biome.BEACH, vanillaChunk.getBiome(0, 4, 0));
        Assertions.assertEquals(Biome.BAMBOO_JUNGLE, vanillaChunk.getBiome(0, 8, 0));
        Assertions.assertEquals(Biome.BIRCH_FOREST, vanillaChunk.getBiome(0, 12, 0));

        // Test non even number cord
        vanillaChunk.setBiome(Biome.DARK_FOREST, 0, 15, 0);
        Assertions.assertEquals(Biome.DARK_FOREST, vanillaChunk.getBiome(0, 12, 0));
    }

    public static void main(String[] args) {
        VanillaPaletteChunk a = VanillaPaletteChunk.of(0,0,DimensionType.OVERWORLD);
        for (int z = 0; z < 15; z++) {
            for (int x = 0; x < 15; x++) {
                a.setBlock(x,0,z,(char)(x*z));
            }
        }

        FriendlyBuffer buffer = new FriendlyBuffer(4096);

        System.out.println("BITS? " + a.getSection(0).getChunkData().getStorage().getBitsPerEntry());
        PaletteWriter.writeChunkSection(buffer, a.getSection(0));
        System.out.println(Arrays.toString(buffer.compress()));

        VanillaChunk b = VanillaChunk.of(0,0,DimensionType.OVERWORLD);
        for (int z = 0; z < 15; z++) {
            for (int x = 0; x < 15; x++) {
                b.setBlock(x,0,z,(char)(x*z));
            }
        }

        buffer = new FriendlyBuffer(4096);
        System.out.println("BITS? " + b.getSection(0).calculateBitsPerEntry());
        b.getSection(0).serializeBlocks(buffer);
        System.out.println(Arrays.toString(buffer.compress()));
    }

    private static VanillaChunk newVanillaChunk() {
        return VanillaChunk.of(1, 2, DimensionType.OVERWORLD);
    }

    private static VanillaPaletteChunk newVanillaPaletteChunk() {
        return VanillaPaletteChunk.of(1, 2, DimensionType.OVERWORLD);
    }
}
