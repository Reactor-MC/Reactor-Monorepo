package ink.reactor.api.world;

import ink.reactor.api.world.chunk.ChunkBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Getter
public final class WorldManager {

    private final List<World> worlds = new ArrayList<>();
    private final Supplier<ChunkBuilder> supplierChunkBuilder;
    private World defaultWorld;

    public WorldManager(Supplier<ChunkBuilder> supplierChunkBuilder, World defaultWorld) {
        this.supplierChunkBuilder = supplierChunkBuilder;
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

    public ChunkBuilder getChunkBuilder() {
        return supplierChunkBuilder.get();
    }
}