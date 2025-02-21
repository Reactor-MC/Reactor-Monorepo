package ink.reactor.server.player;

import ink.reactor.api.Reactor;
import ink.reactor.api.player.Player;
import ink.reactor.api.player.connection.ProtocolConnector;
import ink.reactor.api.scheduler.TickDuration;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public final class PlayerProtocolConnector {

    public static void start(final Collection<Player> players, final Map<UUID, ReactorPlayer> byUUID) {
        Reactor.getServer().getScheduler().schedule(() -> {
            for (final Player player : players) {
                player.getConnection().ping();
            }
        }, TickDuration.ofSeconds(1), new TickDuration(Reactor.getServer().getConfig().pingWaitUpdateTicks()));

        final ProtocolConnector.ProtocolPlayerCreator creator = (name, uuid, connection) -> {
            final ReactorPlayer reactorPlayer = new ReactorPlayer(name,uuid,connection);

            Reactor.getServer().getScheduler().runNow(() -> {
                players.add(reactorPlayer);
                byUUID.put(reactorPlayer.getUuid(), reactorPlayer);

                Reactor.getServer().getWorldManager().getDefaultWorld().addPlayer(reactorPlayer);
            });

            return reactorPlayer;
        };

        final Consumer<Player> cleanup = (player) -> {
            Reactor.getServer().getScheduler().runNow(() -> {
                players.remove(player);
                byUUID.remove(player.getUuid());
                player.getWorld().removePlayer(player);
            });
        };

        ProtocolConnector.setConnector(creator, cleanup);
    }
}
