package ink.reactor.item.component;

import static ink.reactor.item.component.ItemComponent.*;

import ink.reactor.buffer.writer.DynamicSizeBuffer;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectMap.Entry;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectMap.FastEntrySet;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ItemComponentSerializer {

    public static void serialize(final Byte2ObjectOpenHashMap<Object> components, final DynamicSizeBuffer buffer) {
        final FastEntrySet<Object> entries = components.byte2ObjectEntrySet();

        for (final Entry<Object> entry : entries) {
            final Object component = entry.getValue();

            buffer.writeByte(entry.getByteKey()); // Component id

            if (!trySerialize(entry.getByteKey(), component, buffer)) {
                buffer.revert(1);
            }
        }
    }

    private static boolean trySerialize(final byte id, final Object component, final DynamicSizeBuffer buffer) {
        switch (id) {
            case CUSTOM_DATA:
                return CustomData.serializeNBT(buffer, component);
            case CUSTOM_MODEL_DATA:
                return CustomData.serializeModelData(buffer, component);

            // Basic types: String, int, double, float, boolean, enums, etc
            case MAX_STACK_SIZE, MAX_DAMAGE, DAMAGE, REPAIR_COST:
                return Basic.serializeInt(buffer, component);
            case UNBREAKABLE, ENCHANTMENT_GLINT_OVERRIDE, ENCHANTABLE: 
                Basic.serializeBoolean(buffer, component);
                break;
            case ITEM_MODEL: 
                Basic.serializeString(buffer, component);
                break;
            case INTANGIBLE_PROJECTILE:
                Basic.serializeEmptyNBT(buffer, component);
                break;
            case RARITY:
                return Basic.serializeEnum(buffer, component);

            // Food types
            case FOOD:
                return ConsumableComponent.serializeFood(buffer, component);
            case CONSUMABLE:
                return ConsumableComponent.serializeConsumable(buffer, component);

            // Others
            case HIDE_ADDITIONAL_TOOLTIP, HIDE_TOOLTIP, CREATIVE_SLOT_LOCK:
                break;
            case CUSTOM_NAME, ITEM_NAME:
                Chat.serializeText(buffer, component);
                break;
            case LORE:
                Chat.serializeLore(buffer, component);
                break;

            default:
                return false;
        }
        return true;
    }
}