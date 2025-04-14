package ink.reactor.world.chunk.vanilla.serializer;

import ink.reactor.buffer.writer.DynamicSizeBuffer;
import ink.reactor.buffer.writer.WriteBuffer;
import ink.reactor.world.chunk.vanilla.VanillaChunk;
import ink.reactor.world.chunk.vanilla.VanillaChunkSection;

public interface VanillaChunkSerializer {
    VanillaChunkSerializer PALETTE_FORMAT = new VanillaChunkPaletteFormatSerializer();

    byte[] serialize(final VanillaChunk chunk);
    byte[] serializeChunkData(final VanillaChunk chunk);
    byte[] serializeLightData(final VanillaChunkSection[] sections);

    void writeHeader(final VanillaChunk chunk, final WriteBuffer buffer);
    void writeHeightmaps(final VanillaChunk chunk, final DynamicSizeBuffer buffer);
}
