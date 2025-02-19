package ink.reactor.protocol.handler.play;

import ink.reactor.protocol.PlayerConnectionImpl;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.InProtocol;
import ink.reactor.protocol.inbound.PacketInData;

final class ClientPlayKeepAlive implements PacketHandler {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        connection.getKeepAliveManager().onKeepAlive(data.buffer.readLong());
    }

    @Override
    public int packetId() {
        return InProtocol.PLAY_KEEP_ALIVE;
    }
}
