package ink.reactor.protocol.handler.storage;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.PlayerConnectionImpl;

record SingleHandlerStorage(PacketHandler handler) implements HandlerStorageType {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        handler.handle(connection, packetId, data);
    }

    @Override
    public HandlerStorageType remove(PacketHandler handler) {
        return this.handler.equals(handler) ? null : this;
    }
}
