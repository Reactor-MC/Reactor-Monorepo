package ink.reactor.protocol.handler.play;

import ink.reactor.protocol.outbound.play.PacketOutGameEvent;
import ink.reactor.protocol.outbound.play.PacketOutLoginPlay;
import ink.reactor.protocol.outbound.play.PacketOutPlayerPosition;
import ink.reactor.util.EventDispatcher;
import ink.reactor.api.Reactor;
import ink.reactor.api.player.event.PlayerJoinEvent;
import ink.reactor.api.world.WorldManager;
import ink.reactor.protocol.ConnectionState;
import ink.reactor.protocol.PlayerConnectionImpl;

public final class PlayHandler {

    public static void startPlay(final PlayerConnectionImpl connection) {
        final WorldManager worldManager = Reactor.getServer().getWorldManager();

        connection.sendPackets(
            new PacketOutLoginPlay(connection,worldManager.getDefaultWorld()),
            new PacketOutPlayerPosition(connection.getPlayer()),
            new PacketOutGameEvent(PacketOutGameEvent.WAITING_FOR_LEVEL_CHUNKS, 0F)
        );

        EventDispatcher.call(new PlayerJoinEvent(connection.getPlayer()));
    }

    public static void registerHandlers() {
        ConnectionState.PLAY.add(
            new ClientPlayKeepAlive(),
            new ClientPlayPing()
        );
    }
}
