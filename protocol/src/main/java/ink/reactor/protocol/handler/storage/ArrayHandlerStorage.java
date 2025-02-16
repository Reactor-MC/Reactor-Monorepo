package ink.reactor.protocol.handler.storage;

import java.util.Arrays;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.PlayerConnectionImpl;

public final class ArrayHandlerStorage implements HandlerStorageType {

    private PacketHandler[] handlers;

    public ArrayHandlerStorage(PacketHandler old, PacketHandler newHandler) {
        this.handlers = new PacketHandler[] { old, newHandler };
    }

    public void add(final PacketHandler newHandler) {
        handlers = Arrays.copyOf(handlers, handlers.length+1); 
    }

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        for (final PacketHandler handler : handlers) {
            handler.handle(connection, packetId, data);
        }
    }
}