package ink.reactor.item.component;

import static ink.reactor.item.component.ItemComponent.*;

import ink.reactor.item.data.Rarity;
import ink.reactor.nbt.decoder.NBTByteDecoder;
import ink.reactor.buffer.reader.ReadBuffer;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ItemComponentDeserializer {

    public static final Byte2ObjectOpenHashMap<Object> deserialize(final ReadBuffer buffer, final int amountComponents) {
        final Byte2ObjectOpenHashMap<Object> components = new Byte2ObjectOpenHashMap<>(amountComponents);

        for (int i = 0; i < amountComponents; i++) {
            final byte id = buffer.readByte();
            final Object component = loadComponent(components, id, buffer);
            if (component != null) {
                components.put(id, component);
            }
        }
        return components;
    }

    private static Object loadComponent(final Byte2ObjectOpenHashMap<Object> components, final byte id, final ReadBuffer buffer) {
        switch (id) {
            // Basic types: String, int, double, float, boolean, enums, etc
            case MAX_STACK_SIZE, MAX_DAMAGE, DAMAGE, REPAIR_COST:
                return buffer.readVarInt();
            case UNBREAKABLE, ENCHANTMENT_GLINT_OVERRIDE, ENCHANTABLE: 
                return buffer.readBoolean();
            case ITEM_MODEL:
                return buffer.readString();
            case RARITY:
                return Rarity.byId(buffer.readVarInt());

            // Food types
            case FOOD:
                return ConsumableComponent.deserializeFood(buffer);
            case CONSUMABLE:
                return ConsumableComponent.deserializeConsumable(buffer);

            // Others
            case HIDE_ADDITIONAL_TOOLTIP, HIDE_TOOLTIP, CREATIVE_SLOT_LOCK:
                return true;

            // NBT type
            case CUSTOM_DATA, CUSTOM_MODEL_DATA, CUSTOM_NAME, ITEM_NAME, LORE, INTANGIBLE_PROJECTILE:
                return NBTByteDecoder.decode(buffer, false);
        }
        return null;
    }
}
