package ink.reactor.protocol.handler.login;

import org.tinylog.Logger;

import ink.reactor.api.player.connection.ProtocolConnector;
import ink.reactor.protocol.PlayerConnectionImpl;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.login.PacketInLoginStart;
import ink.reactor.protocol.outbound.login.PacketOutLoginSuccess;

final class HelloHandler implements PacketHandler {

    @Override
    public void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data) {
        final PacketInLoginStart packetInLoginStart = new PacketInLoginStart();
        packetInLoginStart.read(data);

        connection.sendPacket(new PacketOutLoginSuccess(packetInLoginStart.uuid, packetInLoginStart.username));
        connection.setPlayer(ProtocolConnector.getPlayerCreator().create(packetInLoginStart.username, packetInLoginStart.uuid, connection));

        Logger.info("Player {} is trying to login", packetInLoginStart.username);
    }

    @Override
    public int packetId() {
        return InProtocol.LOGIN_HELLO;
    }
}
