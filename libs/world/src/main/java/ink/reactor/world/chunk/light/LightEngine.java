package ink.reactor.world.chunk.light;

public interface LightEngine {

    byte MAX_LIGHT_LEVEL = 15, MIN_LIGHT_LEVEL = 0;

    LightHolder createLightHolder();
}