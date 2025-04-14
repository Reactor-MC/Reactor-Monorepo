package ink.reactor.world.chunk.light;

public interface LightHolder {
    byte[] getSkyLight();
    byte[] getBlockLight();
    boolean isReadOnly();

    // Has all zeros for its Skylight data
    boolean isEmptySkyLight();

    // Has all zeros for its Block Light data
    boolean isEmptyBlockLight();
}