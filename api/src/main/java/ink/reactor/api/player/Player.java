package ink.reactor.api.player;

import java.util.UUID;

import ink.reactor.api.Reactor;
import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.api.player.data.Gamemode;
import ink.reactor.api.player.data.PlayerInventory;
import ink.reactor.api.player.data.PlayerSkin;
import ink.reactor.api.plugin.service.permission.PermissionService;
import ink.reactor.chat.ChatMode;
import ink.reactor.chat.component.ChatComponent;

import ink.reactor.command.CommandSender;
import ink.reactor.entity.Entity;

import ink.reactor.entity.type.MinecraftEntity;
import ink.reactor.entity.type.adapter.LivingMetadata;
import ink.reactor.entity.type.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class Player implements CommandSender, MinecraftEntityMetadata, LivingMetadata {
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();

    private final int id = Entity.ENTITY_ID_COUNTER.incrementAndGet();
    private final PlayerSkin skin = new PlayerSkin();

    private final PlayerConnection connection;
    private final String name;
    private final UUID uuid;

    private Gamemode gamemode = Gamemode.SURVIVAL;
    private long ping;

    private String clientInfo;

    private String locale;
    private byte viewDistance;
    private ChatMode chatMode;
    private boolean chatColors;
    private int mainHand;
    private boolean textFiltering;
    private boolean serverListings;

    private float experience;

    private Object world;

    public abstract PlayerInventory getInventory();
    public abstract void setTabHeaderFooter(final ChatComponent[] header, final ChatComponent[] footer);

    public abstract void showTitle(final ChatComponent[] title);
    public abstract void showTitle(final ChatComponent[] title, final ChatComponent[] subtitle);
    public abstract void showTitle(final ChatComponent[] title, final ChatComponent[] subtitle, int fadeIn, int stay, int fadeOut);
    public abstract void clearTitles();

    public abstract void sendActionBar(final ChatComponent[] component);

    public abstract void setLevel(int level);
    public abstract void setExperience(float experience);
    public abstract int getLevel();
    public abstract float getRequiredExperience();

    public void sendPacket(final PacketOutbound packetOutbound) {
        getConnection().sendPacket(packetOutbound);
    }

    @Override
    public boolean isOp() {
        final PermissionService service = Reactor.getServer().getPluginManager().getData(PermissionService.class);
        return (service != null && service.isOp(name));
    }

    @Override
    public boolean hasPermission(String permission) {
        final PermissionService service = Reactor.getServer().getPluginManager().getData(PermissionService.class);
        return (service != null && service.hasPermission(name, permission));
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public final boolean equals(Object obj) {
        return obj == this || (obj instanceof Player player && player.uuid.equals(this.uuid));
    }

    @Override
    public final int hashCode() {
        return id;
    }
}
