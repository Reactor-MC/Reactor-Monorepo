package ink.reactor.chat.format.minimessage.tag;

import java.util.List;

import ink.reactor.chat.ChatColor;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.ColoredComponent;
import ink.reactor.chat.format.minimessage.MessageTag;
import ink.reactor.chat.format.minimessage.MiniMessage;

public final class ColorTags {

    public static void registerTags() {
        registerTag(ChatColor.BLACK);
        registerTag(ChatColor.DARK_BLUE);
        registerTag(ChatColor.DARK_GREEN);
        registerTag(ChatColor.DARK_AQUA);
        registerTag(ChatColor.DARK_RED);
        registerTag(ChatColor.DARK_PURPLE);
        registerTag(ChatColor.GOLD);
        registerTag(ChatColor.GRAY);
        registerTag(ChatColor.DARK_GRAY);
        registerTag(ChatColor.BLUE);
        registerTag(ChatColor.GREEN);
        registerTag(ChatColor.AQUA);
        registerTag(ChatColor.RED);
        registerTag(ChatColor.LIGHT_PURPLE);
        registerTag(ChatColor.YELLOW);
        registerTag(ChatColor.WHITE);
        
        MiniMessage.registerTag(new ColorTag(), "color");
    }

    private static void registerTag(final ChatColor color) {
        MiniMessage.registerTag(chatColorTag(color), color.getName());
    }

    public static MessageTag chatColorTag(final ChatColor color) {
        return (isCloseTag, message, args, output) -> {
            if (isCloseTag) {
                output.add(new ColoredComponent("", ChatColor.WHITE));
                return;
            }
            output.add(new ColoredComponent(message, color));
        };
    }

    private static final class ColorTag implements MessageTag {
        @Override
        public void parse(boolean isCloseTag, String message, List<String> args, List<ChatComponent> output) {
            if (isCloseTag) {
                output.add(new ColoredComponent("", ChatColor.WHITE));
                return;
            }
            if (args.get(1).isEmpty()) { // when not color is set <color:>
                output.add(new ColoredComponent(message, ChatColor.WHITE));
                return;
            }
            ChatColor color = ChatColor.byLegacyName(message);
            if (color == null) {
                color = ChatColor.hex(args.get(1));
            }
            output.add(new ColoredComponent(message, color));
        }
    }
}
