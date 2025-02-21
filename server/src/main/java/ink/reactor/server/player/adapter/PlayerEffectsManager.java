package ink.reactor.server.player.adapter;

import ink.reactor.api.player.Player;
import ink.reactor.entity.effect.MobEffect;
import ink.reactor.entity.effect.MobEffectManager;
import ink.reactor.item.data.potion.PotionEffectType;
import ink.reactor.protocol.outbound.play.PacketOutRemovePotionEffect;
import ink.reactor.protocol.outbound.play.PacketOutUpdatePotionEffect;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PlayerEffectsManager extends MobEffectManager {
    private final Player player;

    @Override
    public void tick() {
        System.out.println("EA -" + (effects == null));
        if (effects == null) {
            return;
        }
        final int length = effects.length;
        for (int i = 0; i < length; i++) {
            final MobEffect effect = effects[i];
            if (effect == null || effect.tick()) {
                continue;
            }
            player.getConnection().sendPacket(new PacketOutRemovePotionEffect(player.getId(), effect.getType()));
            effects[i] = null;
            bitmask &= ~(1L << effect.getType().id);
        }
    }

    @Override
    public MobEffect removeEffect(PotionEffectType potionEffectType) {
        final MobEffect effect = super.removeEffect(potionEffectType);
        if (effect != null) {
            player.getConnection().sendPacket(new PacketOutRemovePotionEffect(player.getId(), effect.getType()));
        }
        return effect;
    }

    @Override
    public boolean addEffect(final MobEffect effect) {
        boolean addEffect = super.addEffect(effect);
        if (addEffect) {
            player.getConnection().sendPacket(new PacketOutUpdatePotionEffect(player.getId(), effect, true, true, true, true));
        }
        return addEffect;
    }
}
