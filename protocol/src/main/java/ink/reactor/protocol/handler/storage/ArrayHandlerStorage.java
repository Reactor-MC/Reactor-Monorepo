package ink.reactor.protocol.handler.storage;

import java.util.Arrays;

import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.PlayerConnectionImpl;

final class ArrayHandlerStorage implements HandlerStorageType {

    private PacketHandler[] handlers;

    ArrayHandlerStorage(PacketHandler[] handlers) {
        this.handlers = handlers;
    }

    ArrayHandlerStorage(PacketHandler old, PacketHandler newHandler) {
        this.handlers = new PacketHandler[] { old, newHandler };
    }

    public void add(final PacketHandler newHandler) {
        handlers = Arrays.copyOf(handlers, handlers.length+1);
        handlers[handlers.length - 1] = newHandler;
    }

    @Override
    public void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data) {
        for (final PacketHandler handler : handlers) {
            handler.handle(connection, packetId, data);
        }
    }

    @Override
    public HandlerStorageType remove(PacketHandler handler) {
        int index = -1;
        for (int i = 0; i < handlers.length; i++) {
            if (handler.equals(handlers[i])) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return this;
        }
        if (handlers.length == 2) {
            return new SingleHandlerStorage(handlers[index == 0 ? 1 : 0]);
        }
        if (handlers.length == 1) {
            return null;
        }

        final PacketHandler[] newHandlers = new PacketHandler[handlers.length - 1];

        System.arraycopy(handlers, 0, newHandlers, 0, index);
        System.arraycopy(handlers, index + 1, newHandlers, index, handlers.length - index - 1);

        return new ArrayHandlerStorage(newHandlers);
    }
}