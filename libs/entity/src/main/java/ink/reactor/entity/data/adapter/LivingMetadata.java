package ink.reactor.entity.data.adapter;

import ink.reactor.entity.data.Living;

public interface LivingMetadata {
    Living getLiving();

    default float getYaw() {
        return getLiving().getYaw();
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
}