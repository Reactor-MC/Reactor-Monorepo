package ink.reactor.protocol.handler;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.inbound.PacketDecoded;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class SingleHandlerManager implements PacketHandlerManager {

    private final PacketHandler handler;

    @Override
    public PacketHandlerManager add(PacketHandler handler) {
        return new ArrayHandlerManager(new PacketHandler[] {this.handler, handler});
    }

    @Override
    public PacketHandlerManager remove(PacketHandler handler) {
        return this.handler.equals(handler) ? null : this;
    }

    @Override
    public void execute(final PlayerConnection connection, final PacketDecoded packetDecoded) {
        handler.handle(connection, packetDecoded.id(), packetDecoded.data());
    }
}