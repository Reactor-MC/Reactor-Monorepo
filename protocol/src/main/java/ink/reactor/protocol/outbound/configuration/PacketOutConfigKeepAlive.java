package ink.reactor.protocol.outbound.configuration;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutConfigKeepAlive implements PacketOutbound {
    private final long payload;

    public PacketOutConfigKeepAlive(long payload) {
        this.payload = payload;
    }

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer expectedSizeBuffer = new ExpectedSizeBuffer(DataSize.LONG);
        expectedSizeBuffer.writeLong(payload);
        return expectedSizeBuffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.CONFIGURATION_KEEP_ALIVE;
    }
}
