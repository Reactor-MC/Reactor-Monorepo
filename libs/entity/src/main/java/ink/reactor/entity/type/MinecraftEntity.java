package ink.reactor.entity.type;

import ink.reactor.entity.type.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinecraftEntity implements MinecraftEntityMetadata {
    private boolean onFire, sneaking, swimming, invisible, glowingEffect;
    private double x, y, z;
    private double velocityX, velocityY, velocityZ;

    @Override
    public MinecraftEntity getMinecraftEntity() {
        return this;
    }
}