package ink.reactor.server.world.tick;

import ink.reactor.api.Reactor;
import ink.reactor.api.world.World;

import java.util.Collection;

public final class WorldUpdateTimeTask implements Runnable {

    @Override
    public void run() {
        final Collection<World> worlds = Reactor.getServer().getWorldManager().getWorlds();
        for (final World world : worlds) {
            world.setTicks(world.getTicks() + 20L);
        }
    }
}