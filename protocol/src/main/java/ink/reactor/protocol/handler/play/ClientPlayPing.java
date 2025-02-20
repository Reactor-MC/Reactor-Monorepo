package ink.reactor.protocol.handler.play;

import ink.reactor.protocol.PlayerConnectionImpl;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.outbound.play.PacketOutPing;

final class ClientPlayPing implements PacketHandler {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        final long ping = System.currentTimeMillis() - connection.getLastPing();
        if (data.buffer.readInt() == PacketOutPing.PING_ID) {
            connection.getPlayer().setPing(ping);
        }
    }

    @Override
    public int packetId() {
        return InProtocol.PLAY_PONG;
    }
}
