package ink.reactor.world.chunk.light;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public final class StaticLightEngine implements LightEngine {

    // Common cases
    private static DarknessLightHolder darknessLightHolder;
    private static SpecificLightHolder fullBrightLightHolder;

    private final LightHolder lightHolder;

    private StaticLightEngine(LightHolder lightHolder) {
        this.lightHolder = lightHolder;
    }

    @Override
    public LightHolder createLightHolder() {
        return lightHolder;
    }

    public static StaticLightEngine ofBrightness() {
        return ofLevel(MAX_LIGHT_LEVEL);
    }

    public static StaticLightEngine ofDarkness() {
        return ofLevel(MIN_LIGHT_LEVEL);
    }

    public static StaticLightEngine ofLevel(final int lightLevel) {
        final LightHolder holder;

        if (lightLevel <= 0) {
            holder = (darknessLightHolder == null)
                    ? darknessLightHolder = new DarknessLightHolder()
                    : darknessLightHolder;
        } else if (lightLevel >= 15) {
            holder = (fullBrightLightHolder == null)
                    ? fullBrightLightHolder = new SpecificLightHolder(15)
                    : fullBrightLightHolder;
        } else {
            holder = new SpecificLightHolder(lightLevel);
        }

        return new StaticLightEngine(holder);
    }

    private static final class DarknessLightHolder implements LightHolder {
        private final byte[] emptyArray = new byte[2048];
        @Override
        public byte[] getSkyLight() {
            return emptyArray;
        }
        @Override
        public byte[] getBlockLight() {
            return emptyArray;
        }
        @Override
        public boolean isReadOnly() {
            return true;
        }
        @Override
        public boolean isEmptyBlockLight() {
            return true;
        }
        @Override
        public boolean isEmptySkyLight() {
            return true;
        }
    }

    private static final class SpecificLightHolder implements LightHolder {
        private final byte[] array = new byte[2048];
        private SpecificLightHolder(int level) {
            Arrays.fill(array, (byte) (level & 15));
        }
        @Override
        public byte[] getSkyLight() {
            return array;
        }
        @Override
        public byte[] getBlockLight() {
            return array;
        }
        @Override
        public boolean isReadOnly() {
            return true;
        }
        @Override
        public boolean isEmptySkyLight() {
            return false;
        }
        @Override
        public boolean isEmptyBlockLight() {
            return false;
        }
    }
}