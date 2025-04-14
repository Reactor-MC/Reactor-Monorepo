package ink.reactor.protocol.outbound.play.chunk;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.world.chunk.vanilla.VanillaChunk;
import ink.reactor.world.chunk.vanilla.serializer.VanillaChunkSerializer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutChunkUpdateData implements PacketOutbound {
    private final byte[] chunk;

    public PacketOutChunkUpdateData(final VanillaChunk vanillaPaletteChunk) {
        this.chunk = VanillaChunkSerializer.PALETTE_FORMAT.serialize(vanillaPaletteChunk);
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
