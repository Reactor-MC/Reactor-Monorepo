package ink.reactor.server.network.handler.manager;

import ink.reactor.api.player.network.PlayerConnection;
import ink.reactor.protocol.Packet;
import ink.reactor.server.network.handler.PacketHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class SinglePacketManager implements PacketManager {

    private final PacketHandler handler;

    @Override
    public PacketManager add(PacketHandler handler) {
        return new ArrayPacketManager(new PacketHandler[] {this.handler, handler});
    }

    @Override
    public PacketManager remove(PacketHandler handler) {
        return this.handler.equals(handler) ? null : this;
    }

    @Override
    public void execute(final PlayerConnection connection, final Packet packetDecoded) {
        handler.handle(connection, packetDecoded.id(), packetDecoded.data());
    }
}
