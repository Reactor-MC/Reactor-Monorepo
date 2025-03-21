package ink.reactor.world.chunk.vanilla.array;

import ink.reactor.util.buffer.writer.FriendlyBuffer;
import ink.reactor.world.data.WorldType;

import java.util.Arrays;

public final class VanillaChunkSerializer {

    public static byte[] serializeBlocksAndLight(final VanillaChunk vanillaChunk) {
        final FriendlyBuffer buffer = new FriendlyBuffer(32, 1.25f);

        buffer.writeInt(vanillaChunk.getX());
        buffer.writeInt(vanillaChunk.getZ());

        buffer.writeBytes(vanillaChunk.serializeChunkData());
        buffer.writeBytes(vanillaChunk.serializeLightData());

        return buffer.compress();
    }

}