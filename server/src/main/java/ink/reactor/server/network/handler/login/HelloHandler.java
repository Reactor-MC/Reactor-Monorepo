package ink.reactor.server.network.handler.login;

import ink.reactor.api.player.connection.PlayerConnection;
import org.tinylog.Logger;

import ink.reactor.protocol.ProtocolConnector;
import ink.reactor.server.network.PlayerConnectionImpl;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.login.PacketInLoginStart;
import ink.reactor.protocol.outbound.login.PacketOutLoginSuccess;

final class HelloHandler implements PacketHandler {

    @Override
    public void handle(final PlayerConnection connection, final int packetId, final PacketInData data) {
        final PacketInLoginStart packetInLoginStart = new PacketInLoginStart();
        packetInLoginStart.read(data);

        connection.sendPacket(new PacketOutLoginSuccess(packetInLoginStart.uuid, packetInLoginStart.username));
        ((PlayerConnectionImpl)connection).setPlayer(ProtocolConnector.getPlayerCreator().create(packetInLoginStart.username, packetInLoginStart.uuid, connection));

        Logger.info("Player {} is trying to login", packetInLoginStart.username);
    }

    @Override
    public int packetId() {
        return InProtocol.LOGIN_HELLO;
    }
}
