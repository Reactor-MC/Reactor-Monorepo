package ink.reactor.chat.format.minimessage.tag;

import java.util.List;

import ink.reactor.chat.ChatColor;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.FullComponent;
import ink.reactor.chat.format.minimessage.MiniMessage;
import ink.reactor.chat.format.minimessage.MiniTag;

public final class ColorTags {

    public static void registerTags() {
        for (final ChatColor legacyChatColor : ChatColor.LEGACY_COLORS) {
            registerTag(legacyChatColor);
        }
        MiniMessage.registerTag(new ColorTag(), "color");
    }

    private static void registerTag(final ChatColor color) {
        MiniMessage.registerTag(chatColorTag(color), color.getName());
    }

    public static MiniTag chatColorTag(final ChatColor color) {
        return new MiniTag() {
            @Override
            public void parse(FullComponent fullComponent, List<String> args, List<ChatComponent> output) {
                fullComponent.setColor(color);
            }
            @Override
            public void onClose(FullComponent nextComponent) {
                nextComponent.setColor(ChatColor.WHITE);
            }
            @Override
            public boolean autoCloseableTag() {
                return true;
            }
        };
    }

    private static final class ColorTag implements MiniTag {
        @Override
        public void parse(FullComponent fullComponent, List<String> args, List<ChatComponent> output) {
            if (args.get(1).isEmpty()) { // when not color is set <color:>
                fullComponent.setColor(ChatColor.WHITE);
                return;
            }
            ChatColor color = ChatColor.byLegacyName(fullComponent.getText());
            if (color == null) {
                color = ChatColor.hex(args.get(1));
            }
            fullComponent.setColor(color);
        }

        @Override
        public void onClose(FullComponent nextComponent) {
            nextComponent.setColor(ChatColor.WHITE);
        }
    }
}
