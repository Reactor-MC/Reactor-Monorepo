package ink.reactor.server.network.handler.play;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.server.network.PlayerConnectionImpl;

final class ClientPlayKeepAlive implements PacketHandler {

    @Override
    public void handle(PlayerConnection connection, int packetId, PacketInData data) {
        ((PlayerConnectionImpl)connection).getKeepAliveManager().onKeepAlive(data.buffer.readLong());
    }

    @Override
    public int packetId() {
        return InProtocol.PLAY_KEEP_ALIVE;
    }
}
