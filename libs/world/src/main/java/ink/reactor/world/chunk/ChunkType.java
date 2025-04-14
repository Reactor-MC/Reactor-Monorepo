package ink.reactor.world.chunk;

public record ChunkType(
    String name,
    int id
) {
    public static final ChunkType VANILLA_ARRAY = new ChunkType("vanilla_array", 0);

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj == this || (obj instanceof ChunkType chunkType && chunkType.id == this.id);
    }
}
