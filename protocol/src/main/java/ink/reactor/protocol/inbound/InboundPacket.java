package ink.reactor.protocol.inbound;

import ink.reactor.protocol.PacketData;

public interface InboundPacket {
    void read(final PacketData data);
}
