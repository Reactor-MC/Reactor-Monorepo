package ink.reactor.entity.effect;

import ink.reactor.item.data.potion.PotionEffectType;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class MobEffectManager {
    protected long bitmask;
    protected MobEffect[] effects;

    public void tick() {
        if (effects == null) {
            return;
        }
        final int length = effects.length;
        for (int i = 0; i < length; i++) {
            final MobEffect effect = effects[i];
            if (effect == null || effect.tick()) {
                continue;
            }
            effects[i] = null;
            bitmask &= ~(1L << effect.getType().id);
        }
    }

    public boolean addEffect(final MobEffect effect) {
        bitmask |= 1L << effect.getType().ordinal();
        if (effects == null) {
            effects = new MobEffect[3];
            effects[0] = effect;
            return true;
        }

        final int length = effects.length;
        int freeSpace = -1;

        for (int i = 0; i < length; i++) {
            final MobEffect iteratedEffect = effects[i];
            if (iteratedEffect == null) {
                freeSpace = i;
                continue;
            }
            if (iteratedEffect.getType().id != effect.getType().id) {
                continue;
            }
            if (iteratedEffect.getAmplifier() > effect.getAmplifier() || iteratedEffect.getDuration() >= effect.getDuration()) {
                return false;
            }
            effects[i] = effect;
            return true;
        }
        if (freeSpace != -1) {
            effects[freeSpace] = effect;
            return true;
        }
        this.effects = Arrays.copyOf(effects, length + 2);
        effects[length] = effect;
        return true;
    }

    public MobEffect getEffect(final PotionEffectType effectType) {
        if (!containsEffect(effectType)) {
            return null;
        }
        final int id = effectType.id;
        for (final MobEffect effect : effects) {
            if (effect != null && effect.getType().id == id) {
                return effect;
            }
        }
        return null;
    }

    public MobEffect removeEffect(final PotionEffectType potionEffectType) {
        if (!containsEffect(potionEffectType)) {
            return null;
        }

        final int length = effects.length;
        for (int i = 0; i < length; i++) {
            final MobEffect effect = effects[i];

            if (effect == null || effect.getType().id != potionEffectType.id) {
                continue;
            }
            bitmask &= ~(1L << potionEffectType.id);
            effects[i] = null;
            if (bitmask == 0) {
                effects = null;
            }
            return effect;
        }
        return null;
    }

    public boolean containsEffect(final PotionEffectType potionEffectType) {
        return (bitmask & (1L << potionEffectType.id)) != 0;
    }

    public boolean containsAnyEffect() {
        return bitmask != 0;
    }

    public void clear() {
        bitmask = 0;
        effects = null;
    }
}