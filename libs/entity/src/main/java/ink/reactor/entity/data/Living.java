package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.LivingMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Living implements LivingMetadata {
    private float yaw, pitch;
    private float health;
    private boolean potionEffectAmbient;
    private int arrows;

    @Override
    public Living getLiving() {
        return this;
    }
}