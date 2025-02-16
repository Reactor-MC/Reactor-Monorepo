package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class AreaCloudEffect implements MinecraftEntityMetadata {
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();
    private float radius = 3.0F;
    private boolean showEffectInSinglePoint;
    private Object particle;
}