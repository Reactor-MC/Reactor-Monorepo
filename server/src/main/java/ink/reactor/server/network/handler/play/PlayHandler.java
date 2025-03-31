package ink.reactor.server.network.handler.play;

import ink.reactor.api.config.server.ServerConfig;
import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.protocol.outbound.play.PacketOutGameEvent;
import ink.reactor.protocol.outbound.play.PacketOutLoginPlay;
import ink.reactor.protocol.outbound.play.PacketOutPlayerPosition;
import ink.reactor.util.event.EventDispatcher;
import ink.reactor.api.Reactor;
import ink.reactor.api.player.event.PlayerJoinEvent;
import ink.reactor.protocol.ConnectionState;
import ink.reactor.world.WorldManager;

public final class PlayHandler {

    public static void startPlay(final PlayerConnection connection) {
        final WorldManager worldManager = Reactor.getServer().getWorldManager();
        final ServerConfig.Game game = Reactor.getServer().getConfig().game();

        connection.sendPackets(
            new PacketOutLoginPlay(connection, worldManager.getDefaultWorld(), game.viewDistance(), game.simulationDistance()),
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
