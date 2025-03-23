package ink.reactor.protocol.handler;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.inbound.PacketInData;

public interface PacketHandler {
    void handle(final PlayerConnection connection, final int packetId, final PacketInData data);
    int packetId();
}
