package ink.reactor.entity.type;

import ink.reactor.entity.type.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class FallingBlock implements MinecraftEntityMetadata {
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();
    private int spawnX, spawnY, spawnZ;
}
