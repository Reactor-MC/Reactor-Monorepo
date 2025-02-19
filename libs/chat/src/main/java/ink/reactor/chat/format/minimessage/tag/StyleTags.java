package ink.reactor.chat.format.minimessage.tag;

import ink.reactor.chat.format.minimessage.MiniMessage;

public final class StyleTags {
    
    public static void registerTags() {
        MiniMessage.registerTag(
            (component) -> component.setBold(true),
            (nextComponent) -> nextComponent.setBold(false),
            true,
        "bold", "b");
    
        MiniMessage.registerTag(
            (component) -> component.setItalic(true),
            (nextComponent) -> nextComponent.setItalic(false),
            true,
        "italic", "i");

        MiniMessage.registerTag(
            (component) -> component.setObfuscated(true),
            (nextComponent) -> nextComponent.setObfuscated(false),
            true,
        "obfuscated", "o");
    
        MiniMessage.registerTag(
            (component) -> component.setStrikethrough(true),
            (nextComponent) -> nextComponent.setStrikethrough(false),
            true,
        "strikethrough", "s");

            
        MiniMessage.registerTag(
            (component) -> component.setUnderlined(true),
            (nextComponent) -> nextComponent.setUnderlined(false),
            true,
        "underlined", "u");

        MiniMessage.registerTag(
            (component) -> component.reset(),
            (nextComponent) -> nextComponent.reset(),
            true,
        "reset", "r");
    }
}
