package ink.reactor.server.player.adapter;

import ink.reactor.api.player.Player;
import ink.reactor.entity.type.Living;
import ink.reactor.entity.effect.MobEffectManager;

public final class PlayerLiving extends Living {

    private final Player player;

    private PlayerLiving(Player player) {
        this.player = player;
    }

    @Override
    public MobEffectManager getMobEffectManager() {
        return super.getMobEffectManager();
    }

    @Override
    public void setHealth(float health) {
        super.setHealth(health);
        // Todo: send packet
    }

    public static PlayerLiving create(final Player player) {
        final PlayerLiving playerLiving = new PlayerLiving(player);
        playerLiving.setMobEffectManager(new PlayerEffectsManager(player));
        return playerLiving;
    }
}
