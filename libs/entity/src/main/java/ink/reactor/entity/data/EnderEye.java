package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class EnderEye implements MinecraftEntityMetadata {
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();
    private Object item; // Null = (which behaves as if it were a minecraft:ender_eye)
}
