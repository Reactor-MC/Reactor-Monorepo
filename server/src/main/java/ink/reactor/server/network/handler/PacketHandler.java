package ink.reactor.server.network.handler;

import ink.reactor.api.player.network.PlayerConnection;
import ink.reactor.protocol.PacketData;

public interface PacketHandler {
    void handle(final PlayerConnection connection, final int packetId, final PacketData data);
    int packetId();
}
