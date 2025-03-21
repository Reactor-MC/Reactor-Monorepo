package ink.reactor.protocol.handler.handshake;

import ink.reactor.protocol.ProtocolOptions;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.handshake.PacketInStatus;
import ink.reactor.protocol.outbound.handshake.PacketOutStatus;

import ink.reactor.protocol.ConnectionState;
import ink.reactor.protocol.PlayerConnectionImpl;

public final class HandshakeHandler implements PacketHandler {

    private static final int
        STATUS = 1,
        LOGIN = 2,
        TRANSFER = 3;

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        if (data.buffer.readableBytes() == 0) { // Status Request
            connection.sendPacket(new PacketOutStatus(ProtocolOptions.OPTIONS.getDefaultMotdJson()));
            return;
        }

        final PacketInStatus packetInHandshake = new PacketInStatus();
        packetInHandshake.read(data);

        switch (packetInHandshake.state) {
            case STATUS:
                connection.state = ConnectionState.HANDSHAKE;
                break;
            case LOGIN:
                connection.state = ConnectionState.LOGIN;
                break;
            case TRANSFER:
                // TODO: networkManager.channel.connect()
                connection.getChannel().close();
                break;
            default:
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
