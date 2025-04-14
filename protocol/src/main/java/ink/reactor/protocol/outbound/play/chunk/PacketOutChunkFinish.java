package ink.reactor.protocol.outbound.play.chunk;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutChunkFinish implements PacketOutbound {
    private final int amountChunks;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer expectedSizeBuffer = new ExpectedSizeBuffer(DataSize.varInt(amountChunks));
        expectedSizeBuffer.writeVarInt(amountChunks);
        return expectedSizeBuffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_CHUNK_BATCH_FINISHED;
    }
}
