package ink.reactor.world.chunk.vanilla.serializer;

import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.DynamicSizeBuffer;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import ink.reactor.buffer.writer.WriteBuffer;
import ink.reactor.nbt.writer.NBTByteWriter;
import ink.reactor.world.block.Blocks;
import ink.reactor.world.chunk.vanilla.VanillaChunk;
import ink.reactor.world.chunk.vanilla.VanillaChunkSection;
import ink.reactor.world.chunk.vanilla.palette.VanillaDirectPaletteWriter;
import ink.reactor.world.data.Biome;

import java.util.BitSet;

public class VanillaChunkPaletteFormatSerializer implements VanillaChunkSerializer {

    @Override
    public byte[] serialize(final VanillaChunk chunk) {
        final byte[] chunkData = serializeChunkData(chunk);
        final byte[] lightData = serializeLightData(chunk.getSections());
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer((DataSize.INT * 2) + lightData.length + chunkData.length);

        writeHeader(chunk, buffer);
        buffer.writeBytes(chunkData);
        buffer.writeBytes(lightData);

        return buffer.compress();
    }

    @Override
    public byte[] serializeChunkData(final VanillaChunk chunk) {
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(2048);
        writeHeightmaps(chunk, buffer);

        final byte[] serializedChunkSections = serializeChunkSections(chunk.getSections());
        buffer.writeVarInt(serializedChunkSections.length);
        buffer.writeBytes(serializedChunkSections);

        buffer.writeVarInt(0); // Block Entities
        return buffer.compress();
    }

    public byte[] serializeChunkSections(final VanillaChunkSection[] sections) {
        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(2048);

        for (final VanillaChunkSection section : sections) {
            if (section == null) {
                buffer.writeShort(0); // Non-empty blocks

                // Block Palette
                buffer.writeByte(0); // Single valued Palette (Bits per entry)
                buffer.writeVarInt(Blocks.AIR); // Block Default single value
                buffer.writeVarInt(0); // long[] Data Length

                // Biome Palette
                buffer.writeByte(0); // Single valued Palette (Bits per entry)
                buffer.writeVarInt(Biome.BADLANDS.id()); // Biome Default single value
                buffer.writeVarInt(0); // long[] Data Length
                continue;
            }

            if (section.isEmpty()) {
                buffer.writeShort(0); // Non-empty blocks
                // Block Palette
                buffer.writeByte(0); // Single valued Palette (Bits per entry)
                buffer.writeVarInt(Blocks.AIR); // Default single value
                buffer.writeVarInt(0); // long[] Data Length
            } else {
                buffer.writeShort(section.getNonEmptyBlocks());
                // Block Palette
                buffer.writeByte(VanillaDirectPaletteWriter.CHUNK_BPE);
                VanillaDirectPaletteWriter.writeBlocks(section.getBlocks(), buffer);
            }

            // Biome Palette
            buffer.writeByte(VanillaDirectPaletteWriter.BIOME_BPE);
            VanillaDirectPaletteWriter.writeBiomes(section.getBiomes(), buffer);
        }

        return buffer.compress();
    }

    @Override
    public byte[] serializeLightData(final VanillaChunkSection[] sections) {
        final BitSet nonEmptySkyLight = new BitSet(sections.length);
        final BitSet nonEmptyBlockLight = new BitSet(sections.length);

        final BitSet emptySkyLight = new BitSet(sections.length);
        final BitSet emptyBlockLight = new BitSet(sections.length);

        for (int i = 0; i < sections.length; i++) {
            final VanillaChunkSection section = sections[i];
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

        final DynamicSizeBuffer buffer = new DynamicSizeBuffer(
            (nonEmptySkyLight.cardinality() * 2048) + (nonEmptyBlockLight.cardinality() * 2048));

        buffer.writeBitSet(nonEmptySkyLight);
        buffer.writeBitSet(nonEmptyBlockLight);
        buffer.writeBitSet(emptySkyLight);
        buffer.writeBitSet(emptyBlockLight);

        buffer.writeVarInt(nonEmptySkyLight.cardinality());
        for (final VanillaChunkSection section : sections) {
            if (section != null && !section.getLightHolder().isEmptySkyLight()) {
                buffer.writeVarInt(2048);
                buffer.writeBytes(section.getLightHolder().getSkyLight());
            }
        }

        buffer.writeVarInt(nonEmptyBlockLight.cardinality());
        for (final VanillaChunkSection section : sections) {
            if (section != null && !section.getLightHolder().isEmptyBlockLight()) {
                buffer.writeVarInt(2048);
                buffer.writeBytes(section.getLightHolder().getBlockLight());
            }
        }

        return buffer.compress();
    }

    @Override
    public void writeHeader(final VanillaChunk chunk, final WriteBuffer buffer) {
        buffer.writeInt(chunk.getX());
        buffer.writeInt(chunk.getZ());
    }

    @Override
    public void writeHeightmaps(final VanillaChunk chunk, final DynamicSizeBuffer buffer) {
        NBTByteWriter.writeNBT(chunk.getHeightMap().serialize(), buffer);
    }
}