package ink.reactor.api.world.chunk;

import ink.reactor.api.world.World;
import lombok.NonNull;

import java.util.function.Consumer;

public interface ChunkBuilder {
    ChunkBuilder setX(int x);
    ChunkBuilder setZ(int z);
    ChunkBuilder setWorld(@NonNull World world);

    /**
     * Execute when {@link ChunkBuilder#build} method is called
     *
     * @param consumer Consumer to execute
     */
    ChunkBuilder onBuild(@NonNull Consumer<Chunk> consumer);

    /**
     * Create a instance of chunk and call all onBuilds consumers
     *
     * @return chunk
     * @throws IllegalStateException if World is null
     */
    Chunk build();
}
