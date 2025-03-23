package ink.reactor.server.network.handler.configuration;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.server.network.PlayerConnectionImpl;
import ink.reactor.server.network.handler.play.PlayHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.ConnectionState;

final class ClientAcknowledgeFinish implements PacketHandler {

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        ((PlayerConnectionImpl)connection).setState(ConnectionState.PLAY);
        PlayHandler.startPlay(connection);
    }

    @Override
    public int packetId() {
        return InProtocol.CONFIGURATION_FINISH_CONFIGURATION;
    }
}