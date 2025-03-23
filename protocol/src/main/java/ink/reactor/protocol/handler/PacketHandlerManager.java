package ink.reactor.protocol.handler;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.inbound.PacketDecoded;

public interface PacketHandlerManager {
    void execute(PlayerConnection connection, PacketDecoded packetDecoded);

    PacketHandlerManager add(final PacketHandler handler);
    PacketHandlerManager remove(final PacketHandler handler);

    default PacketHandlerManager add(final PacketHandler... handlers) {
        PacketHandlerManager handlerManager = this;
        for (final PacketHandler handler : handlers) {
            handlerManager = add(handler);
        }
        return handlerManager;
    }

    default PacketHandlerManager remove(final PacketHandler... handlers) {
        PacketHandlerManager handlerManager = this;
        for (final PacketHandler handler : handlers) {
            handlerManager = remove(handler);
        }
        return handlerManager;
    }
}
