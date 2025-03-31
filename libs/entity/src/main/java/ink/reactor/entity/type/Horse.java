package ink.reactor.entity.type;

import ink.reactor.entity.type.adapter.LivingMetadata;
import ink.reactor.entity.type.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Horse implements LivingMetadata, MinecraftEntityMetadata {
    private final Living living = new Living();
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();
    private boolean tame, saddled, bred, eating, rearing, mountOpen;
}
