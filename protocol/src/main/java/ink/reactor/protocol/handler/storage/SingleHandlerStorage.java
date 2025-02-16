package ink.reactor.protocol.handler.storage;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.PlayerConnectionImpl;

public final class SingleHandlerStorage implements HandlerStorageType {

    private final PacketHandler handler;

    public SingleHandlerStorage(PacketHandler handler) {
        this.handler = handler;
    }

    public PacketHandler getHandler() {
        return handler;
    }

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        handler.handle(connection, packetId, data);
    }
}
