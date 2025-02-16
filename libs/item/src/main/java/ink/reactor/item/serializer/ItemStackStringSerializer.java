package ink.reactor.item.serializer;

import ink.reactor.item.ItemStack;
import ink.reactor.item.component.ItemComponent;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectMap.FastEntrySet;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ItemStackStringSerializer {

    public static String toRedeableJson(final ItemStack itemStack) {
        final StringBuilder builder = new StringBuilder();

        builder.append('{');
        if (itemStack.hasComponents()) {
            builder.append("\"components\":{");
            final Byte2ObjectOpenHashMap<Object> components = itemStack.getComponents();
            final int amountComponents = components.size();
            final FastEntrySet<Object> entries = components.byte2ObjectEntrySet();

            int i = 0;
            for (final Byte2ObjectMap.Entry<Object> entry : entries) {
                appendObject(ItemComponent.getName(entry.getByteKey()), builder);
                builder.append(":");

                if (entry.getValue() instanceof Number) {
                    builder.append(entry.getValue());
                } else {
                    appendObject(entry.getValue(), builder);
                }

                if (++i != amountComponents) {
                    builder.append(',');
                }
            }
            builder.append("},");
        }

        if (itemStack.getComponentsToRemove() != null) {
            builder.append("\"componentsToRemove\":[");
            int i = 0;
            final byte[] componentsToRemove = itemStack.getComponentsToRemove();
            for (final byte component : componentsToRemove) {
                appendObject(ItemComponent.getName(component), builder);
                if (++i != componentsToRemove.length) {
                    builder.append(',');
                }
            }
            builder.append("],");
        }

        builder.append("\"amount\":");
        builder.append(itemStack.getAmount());
        builder.append(',');

        builder.append("\"material\":");
        appendObject(itemStack.getMaterial().getName(), builder);

        builder.append('}');
        return builder.toString();
    }

    private static void appendObject(final Object object, final StringBuilder builder) {
        builder.append('"');
        builder.append(object);
        builder.append('"');
    }
}
