package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Firework implements MinecraftEntityMetadata {
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();
    private Object info;
    private int creatorEntityID = UNDEFINED; // Entity ID of entity which used firework (for elytra boosting)
    private boolean showWithAngle; // Is shot at angle (from a crossbow)
}
