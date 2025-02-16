package ink.reactor.entity.data.adapter;

import ink.reactor.entity.data.MinecraftEntity;

public interface MinecraftEntityMetadata {
    int UNDEFINED = -1;

    MinecraftEntity getMinecraftEntity();

    default void setOnFire(boolean onFire) {
        getMinecraftEntity().setOnFire(onFire);
    }

    default void setSneaking(boolean sneaking) {
        getMinecraftEntity().setSneaking(sneaking);
    }

    default void setSwimming(boolean swimming) {
        getMinecraftEntity().setSwimming(swimming);
    }

    default void setInvisible(boolean invisible) {
        getMinecraftEntity().setInvisible(invisible);
    }

    default void setGlowingEffect(boolean glowingEffect) {
        getMinecraftEntity().setGlowingEffect(glowingEffect);
    }

    default void setX(double x) {
        getMinecraftEntity().setX(x);
    }

    default void setY(double y) {
        getMinecraftEntity().setY(y);
    }

    default void setZ(double z) {
        getMinecraftEntity().setZ(z);
    }

    default void setVelocityX(double velocityX) {
        getMinecraftEntity().setVelocityX(velocityX);
    }

    default void setVelocityY(double velocityY) {
        getMinecraftEntity().setVelocityY(velocityY);
    }

    default void setVelocityZ(double velocityZ) {
        getMinecraftEntity().setVelocityZ(velocityZ);
    }

    default boolean isOnFire() {
        return getMinecraftEntity().isOnFire();
    }

    default boolean isSneaking() {
        return getMinecraftEntity().isSneaking();
    }

    default boolean isSwimming() {
        return getMinecraftEntity().isSwimming();
    }

    default boolean isInvisible() {
        return getMinecraftEntity().isInvisible();
    }

    default boolean isGlowingEffect() {
        return getMinecraftEntity().isGlowingEffect();
    }

    default double getX() {
        return getMinecraftEntity().getX();
    }

    default double getY() {
        return getMinecraftEntity().getY();
    }

    default double getZ() {
        return getMinecraftEntity().getZ();
    }

    default double getVelocityX() {
        return getMinecraftEntity().getVelocityX();
    }

    default double getVelocityY() {
        return getMinecraftEntity().getVelocityY();
    }

    default double getVelocityZ() {
        return getMinecraftEntity().getVelocityZ();
    }
}