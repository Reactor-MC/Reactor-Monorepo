package ink.reactor.world;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class WorldManager {

    private final List<World> worlds = new ArrayList<>();
    private World defaultWorld;

    private WorldManager(World defaultWorld) {
        this.defaultWorld = defaultWorld;
    }

    public void setDefault(final World defaultWorld) {
        this.worlds.clear();
        this.defaultWorld = defaultWorld;
    }

    public void setDefaultWorld(World defaultWorld) {
        this.worlds.remove(defaultWorld);
        this.defaultWorld = defaultWorld;
    }

    public World byName(final String name) {
        for (final World world : worlds) {
            if (world.getName().equals(name)) {
                return world;
            }
        }
        return null;
    }

    public static WorldManager createWorldManager(final World defaultWorld) {
        final WorldManager worldManager = new WorldManager(defaultWorld);
        worldManager.worlds.add(defaultWorld);
        worldManager.defaultWorld = defaultWorld;
        return worldManager;
    }
}