package ink.reactor.server.network.handler.handshake;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.outbound.handshake.PacketOutPong;

final class PingHandler implements PacketHandler {

    @Override
    public void handle(final PlayerConnection connection, final int packetId, final PacketInData data) {
        connection.sendPacket(new PacketOutPong(data.buffer.readLong()));
    }

    @Override
    public int packetId() {
        return InProtocol.HANDSHAKE_PING;
    }
}