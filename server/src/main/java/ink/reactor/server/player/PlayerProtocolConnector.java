package ink.reactor.server.player;

import ink.reactor.api.Reactor;
import ink.reactor.api.player.Player;
import ink.reactor.api.player.connection.ProtocolConnector;
import ink.reactor.api.scheduler.TickDuration;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

public final class PlayerProtocolConnector {

    public static void start(final Collection<Player> players, final Map<UUID, ReactorPlayer> byUUID) {
        Reactor.getServer().getScheduler().schedule(() -> {
            for (final Player player : players) {
                player.getConnection().ping();
            }
        }, TickDuration.ofSeconds(1), new TickDuration(Reactor.getServer().getConfig().pingWaitUpdateTicks()));

        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        final ProtocolConnector.ProtocolPlayerCreator creator = (name, uuid, connection) -> {
            readWriteLock.writeLock().lock();

            final ReactorPlayer reactorPlayer = new ReactorPlayer(name,uuid,connection);
            players.add(reactorPlayer);
            byUUID.put(reactorPlayer.getUuid(), reactorPlayer);

            readWriteLock.writeLock().unlock();
            return reactorPlayer;
        };

        final Consumer<Player> cleanup = (player) -> {
            readWriteLock.readLock().lock();

            players.remove(player);
            byUUID.remove(player.getUuid());

            readWriteLock.readLock().unlock();
        };

        ProtocolConnector.setConnector(creator, cleanup);
    }
}
