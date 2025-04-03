package ink.reactor.server.network.handler.manager;

import ink.reactor.api.player.network.PlayerConnection;
import ink.reactor.protocol.Packet;
import ink.reactor.server.network.handler.PacketHandler;

import java.util.Arrays;

public final class ArrayPacketManager implements PacketManager {

    private PacketHandler[] handlers;

    public ArrayPacketManager(PacketHandler[] handlers) {
        this.handlers = handlers;
    }

    @Override
    public void execute(PlayerConnection connection, Packet packet) {
        for (final PacketHandler handler : handlers) {
            handler.handle(connection, packet.id(), packet.data());
        }
    }

    @Override
    public PacketManager add(final PacketHandler handler) {
        handlers = Arrays.copyOf(handlers, handlers.length+1);
        handlers[handlers.length - 1] = handler;
        return this;
    }

    @Override
    public PacketManager add(PacketHandler... handlersToAdd) {
        final PacketHandler[] newHandlers = Arrays.copyOf(handlers, handlers.length + handlersToAdd.length);
        System.arraycopy(handlersToAdd, 0, newHandlers, this.handlers.length, handlersToAdd.length);

        this.handlers = newHandlers;
        return this;
    }

    @Override
    public PacketManager remove(final PacketHandler handler) {
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
            return new SinglePacketManager(handlers[index == 0 ? 1 : 0]);
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
