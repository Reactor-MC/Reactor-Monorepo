package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class MinecraftEntity implements MinecraftEntityMetadata {
    private boolean onFire, sneaking, swimming, invisible, glowingEffect;
    private double x, y, z;
    private double velocityX, velocityY, velocityZ;

    @Override
    public MinecraftEntity getMinecraftEntity() {
        return this;
    }
}