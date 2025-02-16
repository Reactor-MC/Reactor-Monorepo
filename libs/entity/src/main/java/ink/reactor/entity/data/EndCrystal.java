package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class EndCrystal implements MinecraftEntityMetadata {
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();
    private boolean showBottom = true;
    private int beamX, beamY, beamZ;
}
