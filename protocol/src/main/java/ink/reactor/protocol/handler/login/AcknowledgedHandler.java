package ink.reactor.protocol.handler.login;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.ConnectionState;
import ink.reactor.protocol.PlayerConnectionImpl;

final class AcknowledgedHandler implements PacketHandler {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        connection.state = ConnectionState.CONFIGURATION;
        connection.isFirstConfig = false;
    }

    @Override
    public int packetId() {
        return InProtocol.LOGIN_LOGIN_ACKNOWLEDGED;
    }
}