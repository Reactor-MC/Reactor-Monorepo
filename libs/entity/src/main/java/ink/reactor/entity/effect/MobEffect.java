package ink.reactor.entity.effect;

import ink.reactor.item.data.potion.PotionEffectType;
import lombok.Getter;
import lombok.Setter;

@Getter
public final class MobEffect {
    private final PotionEffectType type;
    private final int amplifier;
    @Setter
    private int duration;

    public MobEffect(PotionEffectType type, int amplifier) {
        this.type = type;
        this.amplifier = amplifier;
        this.duration = -1;
    }

    public MobEffect(PotionEffectType type, int amplifier, int duration) {
        this.type = type;
        this.amplifier = amplifier;
        this.duration = duration;
    }

    /**
     * Tick the effect (reduce duration in 1)
     * @return is duration is less than 0 (expired)
     */
    public boolean tick() {
        return --duration > 0;
    }

    public boolean isInfinite() {
        return duration == -1;
    }
}