package ink.reactor.server.network.handler.login;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.ConnectionState;
import ink.reactor.server.network.PlayerConnectionImpl;

final class AcknowledgedHandler implements PacketHandler {

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        final PlayerConnectionImpl playerConnection = (PlayerConnectionImpl)connection;
        playerConnection.state = ConnectionState.CONFIGURATION;
        playerConnection.setFirstConfig(true);
    }

    @Override
    public int packetId() {
        return InProtocol.LOGIN_LOGIN_ACKNOWLEDGED;
    }
}