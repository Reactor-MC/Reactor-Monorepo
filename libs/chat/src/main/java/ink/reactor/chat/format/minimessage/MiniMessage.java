package ink.reactor.chat.format.minimessage;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.FullComponent;
import ink.reactor.chat.format.minimessage.tag.ColorTags;
import ink.reactor.chat.format.minimessage.tag.StyleTags;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class MiniMessage {
 
    private static final Map<String, MiniTag> GLOBAL_TAGS = new Object2ObjectOpenHashMap<>();

    static {
        ColorTags.registerTags();
        StyleTags.registerTags();
    }

    public static List<ChatComponent> format(final String text) {
        return MiniMessageFormater.format(text, GLOBAL_TAGS);
    }

    public static Map<String, MiniTag> getGlobalTags() {
        return GLOBAL_TAGS;
    }

    public static void registerTag(final MiniTag tag, final String... aliases) {
        for (final String alias : aliases) {
            GLOBAL_TAGS.put(alias, tag);
        }
    }

    public static void registerTag(final Consumer<FullComponent> onAdd, final Consumer<FullComponent> onClose, final boolean autoCloseable, final String... aliases) {
        for (final String alias : aliases) {
            GLOBAL_TAGS.put(alias, new MiniTag() {
                public void parse(FullComponent fullComponent, List<String> args, List<ChatComponent> output) {
                    onAdd.accept(fullComponent);
                }
                public void onClose(FullComponent nextComponent) {
                    onClose.accept(nextComponent);
                }
                @Override
                public boolean autoCloseableTag() {
                    return autoCloseable;
                }               
            });
        }
    }
}
