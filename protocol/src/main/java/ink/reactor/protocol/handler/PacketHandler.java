package ink.reactor.protocol.handler;

import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.PlayerConnectionImpl;

public interface PacketHandler {
    void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data);
    int packetId();
}
