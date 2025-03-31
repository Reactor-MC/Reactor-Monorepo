package ink.reactor.entity.type.adapter;

import ink.reactor.entity.type.Living;
import ink.reactor.entity.effect.MobEffect;
import ink.reactor.entity.effect.MobEffectManager;
import ink.reactor.item.data.potion.PotionEffectType;

public interface LivingMetadata {
    Living getLiving();

    default float getYaw() {
        return getLiving().getYaw();
    }

    default MobEffectManager getMobEffectManager() {
        return getLiving().getMobEffectManager();
    }

    default float getPitch() {
        return getLiving().getPitch();
    }

    default float getHealth() {
        return getLiving().getHealth();
    }

    default boolean isPotionEffectAmbient() {
        return getLiving().isPotionEffectAmbient();
    }

    default int getArrows() {
        return getLiving().getArrows();
    }

    default void setYaw(float yaw) {
        getLiving().setYaw(yaw);
    }

    default void setPitch(float pitch) {
        getLiving().setPitch(pitch);
    }

    default void setHealth(float health) {
        getLiving().setHealth(health);
    }

    default void setPotionEffectAmbient(boolean potionEffectAmbient) {
        getLiving().setPotionEffectAmbient(potionEffectAmbient);
    }

    default void setArrows(int arrows) {
        getLiving().setArrows(arrows);
    }

    default boolean addEffect(final MobEffect effect) {
        return getMobEffectManager().addEffect(effect);
    }

    default MobEffect removeEffect(final PotionEffectType type) {
        return getMobEffectManager().removeEffect(type);
    }

    default MobEffect getEffect(final PotionEffectType type) {
        return getMobEffectManager().getEffect(type);
    }
}