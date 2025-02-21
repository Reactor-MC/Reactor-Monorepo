package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.LivingMetadata;
import ink.reactor.entity.effect.MobEffectManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Living implements LivingMetadata {
    private MobEffectManager mobEffectManager;
    private float yaw, pitch;
    private float health = 20f;
    private boolean potionEffectAmbient;
    private int arrows;

    @Override
    public Living getLiving() {
        return this;
    }

    public MobEffectManager getMobEffectManager() {
        if (mobEffectManager == null) {
            this.mobEffectManager = new MobEffectManager();
        }
        return mobEffectManager;
    }
}