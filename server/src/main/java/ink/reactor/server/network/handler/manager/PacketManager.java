package ink.reactor.server.network.handler.manager;

import ink.reactor.api.player.network.PlayerConnection;
import ink.reactor.protocol.Packet;
import ink.reactor.server.network.handler.PacketHandler;

/**
 * Manages packet handlers for processing incoming network packets.
 * Provides functionality to register and unregister packet handlers,
 * and execute packet processing for player connections.
 */
public interface PacketManager {

    /**
     * Executes the packet processing for a specific player connection.
     *
     * @param connection The player connection associated with this packet.
     * @param packet The incoming packet to be processed.
     */
    void execute(PlayerConnection connection, Packet packet);

    /**
     * Registers a new packet handler with this manager.
     *
     * @param handler The packet handler to be added.
     * @return The current PacketManager instance for method chaining.
     */
    PacketManager add(final PacketHandler handler);

    /**
     * Unregisters a packet handler from this manager.
     *
     * @param handler The packet handler to be removed.
     * @return The current PacketManager instance for method chaining.
     */
    PacketManager remove(final PacketHandler handler);

    /**
     * Registers multiple packet handlers with this manager.
     *
     * @param handlers Varargs array of packet handlers to be added.
     * @return The current PacketManager instance for method chaining.
     */
    default PacketManager add(final PacketHandler... handlers) {
        PacketManager handlerManager = this;
        for (final PacketHandler handler : handlers) {
            handlerManager = add(handler);
        }
        return handlerManager;
    }

    /**
     * Unregisters multiple packet handlers from this manager.
     *
     * @param handlers Varargs array of packet handlers to be removed.
     * @return The current PacketManager instance for method chaining.
     */
    default PacketManager remove(final PacketHandler... handlers) {
        PacketManager handlerManager = this;
        for (final PacketHandler handler : handlers) {
            handlerManager = remove(handler);
        }
        return handlerManager;
    }
}
