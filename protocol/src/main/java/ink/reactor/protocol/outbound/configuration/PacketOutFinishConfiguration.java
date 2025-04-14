package ink.reactor.protocol.outbound.configuration;

import ink.reactor.protocol.outbound.CachedPacket;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutFinishConfiguration {
    public static final CachedPacket INSTANCE = CachedPacket.of(ExpectedSizeBuffer.EMPTY_BUFFER, OutProtocol.CONFIGURATION_FINISH_CONFIGURATION);
}
