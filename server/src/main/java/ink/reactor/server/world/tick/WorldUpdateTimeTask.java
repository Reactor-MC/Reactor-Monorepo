package ink.reactor.server.world.tick;

import ink.reactor.api.world.WorldManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class WorldUpdateTimeTask implements Runnable {

    private final WorldManager manager;

    @Override
    public void run() {
        manager.getWorlds().forEach(world -> {
            long currentTicks = world.getTicks();
            long newTicks = currentTicks + 20L;
            world.setTime(newTicks); // Error here
        });
    }
}
