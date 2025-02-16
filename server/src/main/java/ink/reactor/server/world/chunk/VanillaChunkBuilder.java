package ink.reactor.server.world.chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ink.reactor.api.world.World;
import ink.reactor.api.world.chunk.Chunk;
import ink.reactor.api.world.chunk.ChunkBuilder;
import lombok.NonNull;

public final class VanillaChunkBuilder implements ChunkBuilder {

    private List<Consumer<Chunk>> buildConsumers;

    private int x,z;
    private World world;
    
    @Override
    public ChunkBuilder setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public ChunkBuilder setZ(int z) {
        this.z = z;
        return this;
    }

    @Override
    public ChunkBuilder setWorld(@NonNull World world) {
        this.world = world;
        return this;
    }

    @Override
    public ChunkBuilder onBuild(@NonNull Consumer<Chunk> consumer) {
        if (buildConsumers == null) {
            this.buildConsumers = new ArrayList<>(4);
        }
        buildConsumers.add(consumer);
        return this;
    }

    @Override
    public Chunk build() {
        if (world == null) {
            throw new IllegalStateException("World must be set before building the chunk.");
        }
        if (!world.isLoaded()) {
            throw new IllegalStateException("World must be loaded to create a chunk");
        }

        final VanillaChunk chunk = new VanillaChunk(x, z, world);

        if (buildConsumers != null) {
            for (final Consumer<Chunk> consumer : buildConsumers) {
                consumer.accept(chunk);
            }
        }
        return chunk;
    }
}