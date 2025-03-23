package ink.reactor.server.network.handler.handshake;

import ink.reactor.api.Reactor;
import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.handshake.PacketInStatus;
import ink.reactor.protocol.outbound.handshake.PacketOutStatus;

import ink.reactor.protocol.ConnectionState;
import ink.reactor.server.network.PlayerConnectionImpl;

public final class HandshakeHandler implements PacketHandler {

    private static final int
        STATUS = 1,
        LOGIN = 2,
        TRANSFER = 3;

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        if (data.buffer.readableBytes() == 0) { // Status Request
            connection.sendPacket(new PacketOutStatus(Reactor.getServer().getConfig().cachedMotdJson().get()));
            return;
        }

        final PlayerConnectionImpl playerConnection = (PlayerConnectionImpl) connection;
        final PacketInStatus packetInHandshake = new PacketInStatus();
        packetInHandshake.read(data);

        switch (packetInHandshake.state) {
            case STATUS:
                playerConnection.state = ConnectionState.HANDSHAKE;
                break;
            case LOGIN:
                playerConnection.state = ConnectionState.LOGIN;
                break;
            case TRANSFER:
                // TODO: networkManager.channel.connect()
                playerConnection.getChannel().close();
                break;
        }
    }

    @Override
    public int packetId() {
        return InProtocol.HANDSHAKE_INTENTION;
    }

    public static void registerHandlers() {
        ConnectionState.HANDSHAKE.add(new HandshakeHandler(), new PingHandler());
    }
}
