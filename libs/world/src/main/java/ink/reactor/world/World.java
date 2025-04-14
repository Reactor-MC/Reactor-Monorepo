package ink.reactor.world;

import ink.reactor.util.LocationUtil;
import ink.reactor.world.chunk.Chunk;
import ink.reactor.world.data.DimensionType;
import ink.reactor.world.data.Gamerule;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class World {
    private static int WORLDS_AMOUNT = 0;

    private boolean isLoaded = true;

    private final Long2ObjectOpenHashMap<Chunk> chunks;

    private final Gamerule gamerule = new Gamerule();
    private final int id = WORLDS_AMOUNT++;
    private long ticks;

    private final String name;
    private final DimensionType dimensionType;

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
            chunk.clear();
        }
        chunks.clear();
    }

    public void addChunk(@NonNull final Chunk chunk) {
        if (!isLoaded) {
            throw new IllegalStateException("World is unloaded");
        }
        final Chunk oldChunk = chunks.put(LocationUtil.compressXZ(chunk.getX(), chunk.getZ()), chunk);
        if (oldChunk != null) {
            oldChunk.clear();
        }
    }

    public Chunk unloadChunk(final int x, final int z) {
        final Chunk oldChunk = chunks.remove(LocationUtil.compressXZ(x, z));
        if (oldChunk != null) {
            oldChunk.clear();
        }
        return oldChunk;
    }

    public Chunk getChunk(final int x, final int z) {
        return chunks.get(LocationUtil.compressXZ(x, z));
    }
}