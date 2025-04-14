package ink.reactor.protocol.outbound.play.chunk;

import ink.reactor.protocol.outbound.CachedPacket;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutChunkStart {
    public static final CachedPacket INSTANCE = CachedPacket.of(ExpectedSizeBuffer.EMPTY_BUFFER, OutProtocol.PLAY_CHUNK_BATCH_START);
}
