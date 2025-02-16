package ink.reactor.chat.util;

import java.util.ArrayList;
import java.util.List;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.nbt.NBT;
import ink.reactor.nbt.TagNBT;
import ink.reactor.nbt.tags.CompoundTag;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ComponentCombiner {

    /**
     * Combine multiples components in one.
     * Example input: new RawComponent("one"), new RawComponent("two")
     * Output: {"text":"one","extra":[{"text":"two"}]}
     * 
     * @param components an array of components to combine
     * @return json with all components combined
     */
    public static String toJson(final ChatComponent... components) {
        final String json = components[0].toJson();
        if (components.length == 1) {
            return json;
        }   
        final StringBuilder builder = new StringBuilder(json.length() + (components.length * 50));
        builder.append(json.substring(0, json.length()-1));
        builder.append(",\"extra\":[");
        final int size = components.length;
        for (int i = 1; i < size; i++) {
            builder.append(components[i].toJson());
            if (i+1 != size) {
                builder.append(',');
            }
        }
        builder.append("]}");
        return builder.toString();
    }

    /**
     * Combine multiples components in one.
     * Example input: new RawComponent("one"), new RawComponent("two")
     * Output: [{"text":"one"},{"text":"two"}]
     * 
     * @param components an array of components to combine
     * @return json with all components combined
     */
    public static String toJsonArray(final ChatComponent... components) {
        if (components.length == 1) {
            return components[0].toJson();
        }
        final StringBuilder builder = new StringBuilder();
        builder.append('[');
        final int size = components.length;
        for (int i = 0; i < size; i++) {
            builder.append(components[i].toJson());
            if (i+1 != size) {
                builder.append(',');
            }
        }
        builder.append(']');
        return builder.toString();
    }

    /**
     * Combine multiples components in one. same format than
     * @see ComponentCombiner#toJson but in nbt 
     * 
     * @param components an array of components to combine
     * @return nbt with all components combined
     */
    public static NBT toNBT(final ChatComponent... components) {
        final NBT nbt = components[0].toNBT();
        final int size = components.length;

        if (size == 1) {
            return nbt;
        }

        final List<TagNBT> extra = new ArrayList<>(components.length);
        for (int i = 1; i < size; i++) {
            extra.add(new CompoundTag(components[i].toNBT(), null));
        }
        nbt.addList("extra", extra, TagNBT.TAG_COMPOUND);
        return nbt;
    }
}