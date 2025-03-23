package ink.reactor.protocol.handler;

import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.inbound.PacketDecoded;

import java.util.Arrays;

public final class ArrayHandlerManager implements PacketHandlerManager {
    private PacketHandler[] handlers;

    public ArrayHandlerManager(PacketHandler[] handlers) {
        this.handlers = handlers;
    }

    @Override
    public void execute(PlayerConnection connection, PacketDecoded packetDecoded) {
        for (final PacketHandler handler : handlers) {
            handler.handle(connection, packetDecoded.id(), packetDecoded.data());
        }
    }

    @Override
    public PacketHandlerManager add(final PacketHandler handler) {
        handlers = Arrays.copyOf(handlers, handlers.length+1);
        handlers[handlers.length - 1] = handler;
        return this;
    }

    @Override
    public PacketHandlerManager add(PacketHandler... handlersToAdd) {
        final PacketHandler[] newHandlers = Arrays.copyOf(handlers, handlers.length + handlersToAdd.length);
        System.arraycopy(handlersToAdd, 0, newHandlers, this.handlers.length, handlersToAdd.length);

        this.handlers = newHandlers;
        return this;
    }

    @Override
    public PacketHandlerManager remove(final PacketHandler handler) {
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
            return new SingleHandlerManager(handlers[index == 0 ? 1 : 0]);
        }
        if (handlers.length == 1) {
            return null;
        }

        final PacketHandler[] newHandlers = new PacketHandler[handlers.length - 1];

        System.arraycopy(handlers, 0, newHandlers, 0, index);
        System.arraycopy(handlers, index + 1, newHandlers, index, handlers.length - index - 1);

        this.handlers = newHandlers;
        return this;
    }
}
