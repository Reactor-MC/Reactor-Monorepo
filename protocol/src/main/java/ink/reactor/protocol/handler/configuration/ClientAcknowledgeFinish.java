package ink.reactor.protocol.handler.configuration;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.handler.play.PlayHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.ConnectionState;
import ink.reactor.protocol.PlayerConnectionImpl;

final class ClientAcknowledgeFinish implements PacketHandler {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        connection.state = ConnectionState.PLAY;
        PlayHandler.startPlay(connection);
    }

    @Override
    public int packetId() {
        return InProtocol.CONFIGURATION_FINISH_CONFIGURATION;
    }
}