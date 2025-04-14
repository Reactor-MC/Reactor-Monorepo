package ink.reactor.protocol.outbound.handshake;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutPong implements PacketOutbound {

    private final long timestamp;

    public PacketOutPong(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer data = new ExpectedSizeBuffer(DataSize.LONG);
        data.writeLong(timestamp);
        return data.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.HANDSHAKE_PONG;
    }
}
