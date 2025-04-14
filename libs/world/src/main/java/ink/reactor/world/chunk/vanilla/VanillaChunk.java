package ink.reactor.world.chunk.vanilla;

import ink.reactor.world.chunk.Chunk;
import ink.reactor.world.chunk.exception.ChunkException;
import ink.reactor.world.chunk.ChunkType;
import ink.reactor.world.chunk.heightmap.GenericHeightmap;
import ink.reactor.world.chunk.light.LightEngine;
import ink.reactor.world.data.Biome;
import ink.reactor.world.data.DimensionType;
import ink.reactor.world.chunk.light.StaticLightEngine;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class VanillaChunk implements Chunk {
    private final DimensionType type;
    private final int amountNegativeSections;

    private final int x, z;
    private final VanillaChunkSection[] sections;
    private final GenericHeightmap heightMap;

    private @NonNull LightEngine lightEngine;

    public static VanillaChunk of(final int x, final int z, final DimensionType worldType) {
        final VanillaChunkSection[] sections = new VanillaChunkSection[worldType.height() / 16]; // Vanilla chunk section has 16 blocks of height
        final int amountNegativeSections = worldType.minY() < 0 ? Math.abs(worldType.minY() >> 4) : 0;

        final int maxY = (sections.length * 16 - (amountNegativeSections * 16)) - 1;

        final VanillaHeightMap heightMap = VanillaHeightMap.of(sections, worldType.minY(), maxY, worldType.height());
        final LightEngine lightEngine = StaticLightEngine.ofBrightness(); // TODO: Change to vanilla light engine

        return new VanillaChunk(worldType, amountNegativeSections, x, z, sections, heightMap, lightEngine);
    }

    @Override
    public void clear() {
        if (sections == null) {
            return;
        }
        for (int i = 0; i < sections.length; i++) {
            final VanillaChunkSection section = sections[i];
            if (section == null) {
                continue;
            }
            section.setChunk(null);
            section.setLightHolder(null);
            section.setBlocks(null);
            section.setBiomes(null);
            section.setNonEmptyBlocks(-1);
            sections[i] = null;
        }
    }

    @Override
    public int getMinY() {
        return type.minY();
    }

    @Override
    public int getMaxY() {
        return (sections.length * 16 - (amountNegativeSections * 16)-1);
    }

    public VanillaChunkSection getSection(final int y) {
        final int key = (y >> 4) + amountNegativeSections; // "x >> 4" = "x / 16". Remember, chunk sections have 16 blocks of height
        if (key < 0 || key >= sections.length) {
            throwInvalidYCord(y);
        }
        return sections[key];
    }

    @Override
    public char getBlock(final int x, int y, final int z) {
        if (x > 15 || z > 15) {
            throwInvalidXZCord(x, z);
        }

        final VanillaChunkSection section = getSection(y);
        if (section == null) {
            return 0;
        }
        if (y < 0) {
            y = ((amountNegativeSections * 16) + y) & 15;
        } else {
            y &= 15;
        }
        return section.getBlock(x, y, z);
    }

    @Override
    public void setBlock(final int x, int y, final int z, final char blockId) {
        if (x > 15 || z > 15) {
            throwInvalidXZCord(x, z);
        }
        final int key = (y >> 4) + amountNegativeSections;
        if (key < 0 || key >= sections.length) {
            throwInvalidYCord(y);
        }

        VanillaChunkSection section = sections[key];
        if (section == null) {
            section = new VanillaChunkSection();
            section.setChunk(this);
            section.setLightHolder(lightEngine.createLightHolder());
            sections[key] = section;
        }

        final int sectionY = (y >= 0) ? y & 15 : ((amountNegativeSections * 16) + y) & 15;
        section.setBlock(x, sectionY, z, blockId);

        heightMap.setSurface(blockId, x, y, key, z);
    }

    @Override
    public void setBiome(final Biome biome, final int x, final int y, final int z) {
        if (x > 15 || z > 15) {
            throwInvalidXZCord(x, z);
        }
        final VanillaChunkSection section = getSection(y);
        if (section != null) {
            section.setBiome((byte)biome.id(), x, y, z);
        }
    }

    @Override
    public Biome getBiome(final int x, final int y, final int z, final Biome defaultBiome) {
        if (x > 15 || z > 15) {
            throwInvalidXZCord(x, z);
        }
        final VanillaChunkSection section = getSection(y);
        return (section == null) ? defaultBiome : Biome.ALL.get(section.getBiomeId(x, y, z));
    }

    @Override
    public char getHighestBlock(final int x, final int z) {
        return getBlock(x, heightMap.getWorldSurface(x, z), z);
    }

    @Override
    public ChunkType getChunkType() {
        return ChunkType.VANILLA_ARRAY;
    }

    private void throwInvalidYCord(final int y) {
        throw new ChunkException("Y cord need be in the range " + type.minY() + " -> " + getMaxY() + ". But found: " + y);
    }

    private static void throwInvalidXZCord(final int x, final int z) {
        throw new ChunkException("X and Z cords need be 0-15. Found: x:" + x + " z:" + z);
    }
}