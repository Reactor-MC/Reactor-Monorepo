package ink.reactor.api.world.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Gamerule {
    private boolean respawnScreen = true;
    private boolean hardCore = false;

    private int portalCooldown = 20;
}