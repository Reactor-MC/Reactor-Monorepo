package ink.reactor.api.world;

import ink.reactor.api.player.Player;
import ink.reactor.api.world.data.Border;
import ink.reactor.util.LocationUtil;
import ink.reactor.api.world.chunk.Chunk;
import ink.reactor.api.world.data.Biome;
import ink.reactor.api.world.data.Gamerule;
import ink.reactor.api.world.data.WorldType;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class World {
    private static int WORLDS_AMOUNT = 0;

    private boolean isLoaded = true;

    private final Long2ObjectOpenHashMap<Chunk> chunks;
    private final Set<Player> players = new ObjectOpenHashSet<>();

    private final Gamerule gamerule = new Gamerule();
    private final int id = WORLDS_AMOUNT++;
    private long ticks;

    private final String name;
    private final WorldType worldType;
    private final Biome biome;

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof World world && world.id == this.id);
    }

    public void unload() {
        this.isLoaded = false;
        for (final Chunk chunk : chunks.values()) {
            chunk.unload();
        }
        chunks.clear();
    }

    public void addChunk(@NonNull final Chunk chunk) {
        if (!isLoaded) {
            throw new IllegalStateException("World is unloaded");
        }
        final Chunk oldChunk = chunks.put(LocationUtil.compressXZ(chunk.getX(), chunk.getZ()), chunk);
        if (oldChunk != null) {
            oldChunk.unload();
        }
    }

    public Chunk unloadChunk(final int x, final int z) {
        final Chunk oldChunk = chunks.remove(LocationUtil.compressXZ(x, z));
        if (oldChunk != null) {
            oldChunk.unload();
        }
        return oldChunk;
    }

    public Chunk getChunk(final int x, final int z) {
        return chunks.get(LocationUtil.compressXZ(x, z));
    }

    public void addPlayer(final Player player) {
        players.add(player);
        player.setWorld(this);
    }

    public void removePlayer(final Player player) {
        players.remove(player);
        player.setWorld(null);
    }
}
