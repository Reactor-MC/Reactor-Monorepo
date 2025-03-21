package ink.reactor.server.player.processor;

import ink.reactor.api.Reactor;
import ink.reactor.api.player.Player;

import java.util.Collection;

public class PlayerProcessor implements Runnable {

    @Override
    public void run() {
        final Collection<Player> players = Reactor.getServer().getPlayers();
        for (final Player player : players) {
            player.getMobEffectManager().tick();
        }
    }
}
