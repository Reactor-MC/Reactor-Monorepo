package ink.reactor.protocol.handler.handshake;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.outbound.handshake.PacketOutPong;
import ink.reactor.protocol.PlayerConnectionImpl;

final class PingHandler implements PacketHandler {

    @Override
    public void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data) {
        connection.sendPacket(new PacketOutPong(data.buffer.readLong()));
        connection.getChannel().close();
    }

    @Override
    public int packetId() {
        return InProtocol.HANDSHAKE_PING;
    }
}