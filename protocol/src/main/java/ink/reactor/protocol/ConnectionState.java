package ink.reactor.protocol;

import ink.reactor.protocol.handler.ArrayHandlerManager;
import ink.reactor.protocol.handler.PacketHandler;
import ink.reactor.protocol.handler.PacketHandlerManager;
import ink.reactor.protocol.handler.SingleHandlerManager;
import lombok.NonNull;

public enum ConnectionState {
    HANDSHAKE(2),
    LOGIN(6),
    CONFIGURATION(17),
    PLAY(130);

    public final PacketHandlerManager[] handlerManagers;

    ConnectionState(int expectedPackets) {
        this.handlerManagers = new PacketHandlerManager[expectedPackets];
    }

    public void add(final @NonNull PacketHandler handler) {
        final PacketHandlerManager handlerManager = getManager(handler);
        handlerManagers[handler.packetId()] = (handlerManager == null) ? new SingleHandlerManager(handler) : handlerManager.add(handler);
    }

    public void add(final @NonNull PacketHandler... handlers) {
        for (final PacketHandler handler : handlers) {
            add(handler);
        }
    }

    public void remove(final @NonNull PacketHandler handler) {
        final PacketHandlerManager handlerManager = getManager(handler);
        if (handlerManager != null) {
            handlerManagers[handler.packetId()] = handlerManager.remove(handler);
        }
    }

    public void remove(final @NonNull PacketHandler... handlers) {
        for (final PacketHandler handler : handlers) {
            remove(handler);
        }
    }

    private PacketHandlerManager getManager(final PacketHandler packetHandler) {
        final int id = packetHandler.packetId();
        if (id < 0 || id > handlerManagers.length) {
            throw new ArrayIndexOutOfBoundsException("The id " + id + " is invalid. Range of packets id: 0-" + (handlerManagers.length-1) + " . Class: " + packetHandler.getClass());
        }
        return handlerManagers[id];
    }
}