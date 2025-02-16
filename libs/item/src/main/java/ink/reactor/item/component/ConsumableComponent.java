package ink.reactor.item.component;

import ink.reactor.item.data.Food;
import ink.reactor.item.data.consumable.Consumable;
import ink.reactor.item.data.consumable.ConsumableAnimation;
import ink.reactor.util.buffer.reader.ReadBuffer;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

final class ConsumableComponent {

    static boolean serializeFood(final FriendlyBuffer buffer, final Object component) {
        if (component instanceof Food food) {
            buffer.writeVarInt(food.getNutrition());
            buffer.writeFloat(food.getSaturation());
            buffer.writeBoolean(food.isAlwaysEdible());
            return true;
        }
        return false;
    }

    static boolean serializeConsumable(final FriendlyBuffer buffer, final Object component) {
        if (component instanceof Consumable consumable) {
            buffer.writeFloat(consumable.durationSeconds());
            buffer.writeVarInt(consumable.animation().ordinal());
            buffer.writeString(consumable.sound());
            buffer.writeBoolean(consumable.hasParticles());
            buffer.writeVarInt(0); // Effects
            return true;
        }
        return false;
    }

    static Food deserializeFood(final ReadBuffer buffer) {
        final int nutrition = buffer.readVarInt();
        final float saturation = buffer.readFloat();
        final boolean alwaysEdible = buffer.readBoolean();
        return Food.search(nutrition, saturation, alwaysEdible);
    }

    static Consumable deserializeConsumable(final ReadBuffer buffer) {
        final float durationSeconds = buffer.readFloat();
        final ConsumableAnimation animation = ConsumableAnimation.ALL[buffer.readVarInt()];
        final String sound = buffer.readString();
        final boolean hasParticles = buffer.readBoolean();
        buffer.readVarInt(); // Effects
        return new Consumable(durationSeconds, animation, sound, hasParticles);
    }
}
