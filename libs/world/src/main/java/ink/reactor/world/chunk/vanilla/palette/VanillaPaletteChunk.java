package ink.reactor.world.chunk.vanilla.palette;

import ink.reactor.nbt.type.NBTGeneral;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import ink.reactor.world.chunk.Chunk;
import ink.reactor.world.chunk.ChunkType;
import ink.reactor.world.chunk.exception.ChunkException;
import ink.reactor.world.chunk.light.LightEngine;
import ink.reactor.world.chunk.light.StaticLightEngine;
import ink.reactor.world.chunk.vanilla.GenericHeightmap;
import ink.reactor.world.data.Biome;
import ink.reactor.world.data.DimensionType;
import ink.reactor.world.palette.PaletteWriter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.BitSet;

@Getter
@RequiredArgsConstructor
public final class VanillaPaletteChunk implements Chunk {
    private final DimensionType type;
    private final int amountNegativeSections;

    private final int x, z;
    private final VanillaPaletteChunkSection[] sections;
    private final LightEngine lightEngine;
    private final GenericHeightmap heightMap;

    @Override
    public void unload() {

    }

    @Override
    public byte getChunkType() {
        return ChunkType.VANILLA_PALETTE;
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

        VanillaPaletteChunkSection section = sections[key];
        if (section == null) {
            section = new VanillaPaletteChunkSection(this, lightEngine.createLightHolder());
            sections[key] = section;
        }

        final int sectionY = (y >= 0) ? y & 15 : ((amountNegativeSections * 16) + y) & 15;
        section.setBlockId(x, sectionY, z, blockId);
        heightMap.setSurface(blockId, x, y, key, z);
    }

    @Override
    public char getBlock(final int x, int y, final int z) {
        if (x > 15 || z > 15) {
            throwInvalidXZCord(x, z);
        }

        final VanillaPaletteChunkSection section = getSection(y);
        if (section == null) {
            return 0;
        }
        if (y < 0) {
            y = ((amountNegativeSections * 16) + y) & 15;
        } else {
            y &= 15;
        }
        return section.getBlockId(x, y, z);
    }

    @Override
    public void setBiome(final Biome biome, final int x, final int y, final int z) {
        if (x > 15 || z > 15) {
            throwInvalidXZCord(x, z);
        }
        final VanillaPaletteChunkSection section = getSection(y);
        if (section != null) {
            section.setBiome((byte)biome.id(), x, y, z);
        }
    }

    @Override
    public Biome getBiome(final int x, int y, int z, final Biome defaultBiome) {
        if (x > 15 || z > 15) {
            throwInvalidXZCord(x, z);
        }
        final VanillaPaletteChunkSection section = getSection(y);
        return (section == null) ? defaultBiome : Biome.ALL.get(section.getBiomeId(x, y, z));
    }

    @Override
    public char getHighestBlock(final int x, final int z) {
        return 2;
    }

    public VanillaPaletteChunkSection getSection(final int y) {
        final int key = (y >> 4) + amountNegativeSections;
        if (key < 0 || key >= sections.length) {
            throwInvalidYCord(y);
        }
        return sections[key];
    }

    @Override
    public int getAmountBlocks() {
        int nonEmptyBlocks = 0;
        for (final VanillaPaletteChunkSection section : sections) {
            if (section != null) {
                nonEmptyBlocks += section.getNonEmptyBlocks();
            }
        }
        return nonEmptyBlocks;
    }

    @Override
    public int getNonEmptySections() {
        int nonEmptySections = 0;
        for (final VanillaPaletteChunkSection section : sections) {
            if (section != null) {
                nonEmptySections++;
            }
        }
        return nonEmptySections;
    }

    @Override
    public int getMinY() {
        return type.minY();
    }

    @Override
    public int getMaxY() {
        return (sections.length * 16 - (amountNegativeSections * 16)) - 1;
    }

    public static VanillaPaletteChunk of(final int x, final int z, final DimensionType worldType) {
        final VanillaPaletteChunkSection[] sections = new VanillaPaletteChunkSection[worldType.height() / 16]; // Vanilla chunk section has 16 blocks of height
        final int amountNegativeSections = worldType.minY() < 0 ? Math.abs(worldType.minY() >> 4) : 0;

        final int maxY = (sections.length * 16 - (amountNegativeSections * 16)) - 1;

        final GenericHeightmap heightMap = GenericHeightmap.of(sections, worldType.minY(), maxY, worldType.height());
        final LightEngine lightEngine = StaticLightEngine.ofBrightness(); // TODO: Change to vanilla light engine

        return new VanillaPaletteChunk(worldType, amountNegativeSections, x, z, sections, lightEngine, heightMap);
    }

    private void throwInvalidYCord(final int y) {
        throw new ChunkException("Y cord need be in the range " + type.minY() + " -> " + getMaxY() + ". But found: " + y);
    }

    private static void throwInvalidXZCord(final int x, final int z) {
        throw new ChunkException("X and Z cords need be 0-15. Found: x:" + x + " z:" + z);
    }

    public byte[] serializeChunkData() {
        final byte[] heightmaps = serializeHeightMapNBT();
        final byte[] sections = serializeSections();

        final FriendlyBuffer buffer = new FriendlyBuffer(heightmaps.length + sections.length + 1);
        buffer.writeBytes(heightmaps);
        buffer.writeVarInt(sections.length);
        buffer.writeBytes(sections);

        buffer.writeVarInt(0); // Block Entities
        return buffer.compress();
    }

    private byte[] serializeSections() {
        final FriendlyBuffer buffer = new FriendlyBuffer(1024);
        for (final VanillaPaletteChunkSection section : sections) {
            if (section == null) {
                buffer.writeShort(0);
                continue;
            }
            buffer.writeShort(section.getNonEmptyBlocks());
            PaletteWriter.writeDataPalette(buffer, section.getChunkData());
            PaletteWriter.writeDataPalette(buffer, section.getBiomeData());
        }
        return buffer.compress();
    }


    public byte[] serializeLightData() {
        final BitSet nonEmptySkyLight = new BitSet(sections.length);
        final BitSet nonEmptyBlockLight = new BitSet(sections.length);

        final BitSet emptySkyLight = new BitSet(sections.length);
        final BitSet emptyBlockLight = new BitSet(sections.length);

        for (int i = 0; i < sections.length; i++) {
            final VanillaPaletteChunkSection section = sections[i];
            if (section == null) {
                continue;
            }
            if (!section.getLightHolder().isEmptyBlockLight()) {
                nonEmptyBlockLight.set(i);
            } else {
                emptyBlockLight.set(i);
            }
            if (!section.getLightHolder().isEmptySkyLight()) {
                nonEmptySkyLight.set(i);
            } else {
                emptySkyLight.set(i);
            }
        }

        final FriendlyBuffer buffer = new FriendlyBuffer((nonEmptySkyLight.cardinality() * 2048) + (nonEmptyBlockLight.cardinality() * 2048));
        buffer.writeBitSet(nonEmptySkyLight);
        buffer.writeBitSet(nonEmptyBlockLight);
        buffer.writeBitSet(emptySkyLight);
        buffer.writeBitSet(emptyBlockLight);

        buffer.writeVarInt(nonEmptySkyLight.cardinality());
        for (final VanillaPaletteChunkSection section : sections) {
            if (section != null && !section.getLightHolder().isEmptySkyLight()) {
                buffer.writeVarInt(2048);
                buffer.writeBytes(section.getLightHolder().getSkyLight());
            }
        }

        buffer.writeVarInt(nonEmptyBlockLight.cardinality());
        for (final VanillaPaletteChunkSection section : sections) {
            if (section != null && !section.getLightHolder().isEmptyBlockLight()) {
                buffer.writeVarInt(2048);
                buffer.writeBytes(section.getLightHolder().getBlockLight());
            }
        }

        return buffer.compress();
    }

    public byte[] serializeHeightMapNBT() {
        final long[] data = getHeightMap().writeHeightmap();
        final NBTGeneral heightmaps = new NBTGeneral();
        heightmaps.addLongArray("MOTION_BLOCKING", data);
        heightmaps.addLongArray("WORLD_SURFACE", data);
        return NBTByteWriter.writeNBT(heightmaps).compress();
    }

}
