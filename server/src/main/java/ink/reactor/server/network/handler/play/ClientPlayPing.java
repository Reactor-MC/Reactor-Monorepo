package ink.reactor.server.network.handler.play;

import ink.reactor.api.player.connection.PlayerConnection;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.outbound.play.PacketOutPing;
import ink.reactor.server.network.PlayerConnectionImpl;

final class ClientPlayPing implements PacketHandler {

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        final long ping = System.currentTimeMillis() - ((PlayerConnectionImpl)connection).getLastPing();
        if (data.buffer.readInt() == PacketOutPing.PING_ID) {
            connection.getPlayer().setPing(ping);
        }
    }

    @Override
    public int packetId() {
        return InProtocol.PLAY_PONG;
    }
}
