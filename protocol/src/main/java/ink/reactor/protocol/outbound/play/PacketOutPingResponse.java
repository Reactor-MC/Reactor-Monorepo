package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PacketOutPingResponse implements PacketOutbound {

    private final long payload;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(DataSize.LONG);
        buffer.writeLong(payload);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_PONG_RESPONSE;
    }
}
