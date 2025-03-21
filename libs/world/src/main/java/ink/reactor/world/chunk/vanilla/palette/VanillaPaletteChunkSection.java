package ink.reactor.world.chunk.vanilla.palette;

import ink.reactor.world.chunk.ChunkSection;
import ink.reactor.world.chunk.light.LightHolder;
import ink.reactor.world.palette.type.DataPalette;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class VanillaPaletteChunkSection implements ChunkSection {

    private static final int AIR = 0;

    private final VanillaPaletteChunk chunk;
    private int nonEmptyBlocks;
    private DataPalette chunkData;
    private DataPalette biomeData;

    private final LightHolder lightHolder;

    public VanillaPaletteChunkSection(VanillaPaletteChunk chunk, LightHolder lightHolder) {
        this(chunk, 0, DataPalette.createForChunk(), DataPalette.createForBiome(), lightHolder);
    }

    public VanillaPaletteChunkSection(VanillaPaletteChunk chunk, VanillaPaletteChunkSection original, LightHolder lightHolder) {
        this(chunk, original.nonEmptyBlocks, new DataPalette(original.chunkData), new DataPalette(original.biomeData), lightHolder);
    }

    @Override
    public void setBlockId(final int x, final int y, final int z, final char id) {
        int curr = this.chunkData.set(x, y, z, id);
        if (id != AIR && curr == AIR) {
            this.nonEmptyBlocks++;
        } else if (id == AIR && curr != AIR) {
            this.nonEmptyBlocks--;
        }
    }

    @Override
    public char getBlockId(int x, int y, int z) {
        return (char) this.chunkData.get(x, y, z);
    }

    @Override
    public void setBiome(byte biome, int x, int y, int z) {
        biomeData.set((x>>2), (y>>2), (z>>2), biome);
    }

    @Override
    public byte getBiomeId(int x, int y, int z) {
        return (byte)biomeData.get(x >> 2, y >> 2, z >> 2);
    }

    @Override
    public boolean isEmpty() {
        return this.nonEmptyBlocks == 0;
    }
}