package ink.reactor.item.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Firework {
    private Shape shape;
    private int[] rgbExplosionColors;
    private int[] rgbFadeColors;
    private boolean hasTrail;
    private boolean hasTwinkle;

    public static enum Shape {
        SMALL_BALL,
        LARGE_BALL,
        STAR,
        CREEPER,
        BURST
    }
}