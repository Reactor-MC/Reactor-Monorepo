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
    private final byte[] chunk;

    public PacketOutChunkUpdateData(final VanillaChunk vanillaChunk) {
        this.chunk = VanillaChunkSerializer.serializeBlocksAndLight(vanillaChunk);
    }

    public PacketOutChunkUpdateData(final VanillaPaletteChunk vanillaPaletteChunk) {
        this.chunk = VanillaPaletteChunkSerializer.serialize(vanillaPaletteChunk);
    }

    @Override
    public byte[] write() {
        return chunk;
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_LEVEL_CHUNK_WITH_LIGHT;
    }
}
