package ink.reactor.chat.util;

import ink.reactor.chat.ChatColor;

public final class ColorUtil {

    public static ChatColor calculateComplementary(ChatColor color) {
        return ChatColor.rgb(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }

}
