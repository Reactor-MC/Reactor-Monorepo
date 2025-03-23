package ink.reactor.server.network.handler.configuration;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.server.network.PlayerConnectionImpl;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;

final class ClientConfigKeepAlive implements PacketHandler {

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        ((PlayerConnectionImpl)connection).getKeepAliveManager().onKeepAlive(data.buffer.readLong());
    }

    @Override
    public int packetId() {
        return InProtocol.CONFIGURATION_KEEP_ALIVE;
    }
}
