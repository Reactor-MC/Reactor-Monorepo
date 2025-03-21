package ink.reactor.protocol.outbound.play.chunk;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.world.chunk.vanilla.array.VanillaChunk;
import ink.reactor.world.chunk.vanilla.array.VanillaChunkSerializer;
import ink.reactor.world.chunk.vanilla.palette.VanillaPaletteChunk;
import ink.reactor.world.chunk.vanilla.palette.VanillaPaletteChunkSerializer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutChunkUpdateData implements PacketOutbound {
    private final VanillaChunk chunk;

    @Override
    public byte[] write() {
        return VanillaChunkSerializer.serializeBlocksAndLight(chunk);
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_LEVEL_CHUNK_WITH_LIGHT;
    }
}
