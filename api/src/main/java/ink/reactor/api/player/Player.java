package ink.reactor.api.player;

import java.util.UUID;

import ink.reactor.api.command.CommandSender;
import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.api.player.data.Gamemode;
import ink.reactor.api.player.data.PlayerInventory;
import ink.reactor.api.player.data.PlayerSkin;
import ink.reactor.api.world.World;
import ink.reactor.chat.ChatMode;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.entity.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class Player implements CommandSender {

    private final int id = Entity.ENTITY_ID_COUNTER.incrementAndGet();
    private final PlayerSkin skin = new PlayerSkin();

    private final PlayerConnection connection;
    private final String name;
    private final UUID uuid;

    private Gamemode gamemode = Gamemode.SURVIVAL;
    private double health = 20;
    private long ping;

    private String clientInfo;

    private String locale;
    private byte viewDistance;
    private ChatMode chatMode;
    private boolean chatColors;
    private int mainHand;
    private boolean textFiltering;
    private boolean serverListings;

    private double x, y, z;
    private double velocityX, velocityY, velocityZ;
    private float yaw, pitch;
    private World world;

    public abstract PlayerInventory getInventory();
    public abstract void setTabHeaderFooter(final ChatComponent[] header, final ChatComponent[] footer);

    @Override
    public final boolean equals(Object obj) {
        return obj == this || (obj instanceof Player player && player.uuid.equals(this.uuid));
    }

    @Override
    public final int hashCode() {
        return id;
    }
}