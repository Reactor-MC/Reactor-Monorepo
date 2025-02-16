package ink.reactor.protocol.outbound.configuration;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutFinishConfiguration implements PacketOutbound {

    @Override
    public byte[] write() {
        return ExpectedSizeBuffer.EMPTY_BUFFER;
    }

    @Override
    public int getId() {
        return OutProtocol.CONFIGURATION_FINISH_CONFIGURATION;
    }
}
