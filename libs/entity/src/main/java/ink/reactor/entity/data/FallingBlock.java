package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class FallingBlock implements MinecraftEntityMetadata {
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();
    private int spawnX, spawnY, spawnZ;
}
