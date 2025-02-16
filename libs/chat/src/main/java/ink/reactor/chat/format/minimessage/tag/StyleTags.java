package ink.reactor.chat.format.minimessage.tag;

import ink.reactor.chat.component.ColoredComponent;
import ink.reactor.chat.format.minimessage.MiniMessage;

public final class StyleTags {
    
    public static void registerTags() {
        MiniMessage.registerTag((isCloseTag, message, args, output) -> {
            System.out.println("A");
            final ColoredComponent coloredComponent = new ColoredComponent(message);
            coloredComponent.setBold(!isCloseTag);
            output.add(coloredComponent);
        }, "bold", "b");

        MiniMessage.registerTag((isCloseTag, message, args, output) -> {
            final ColoredComponent coloredComponent = new ColoredComponent(message);
            coloredComponent.setItalic(!isCloseTag);
            output.add(coloredComponent);
        }, "italic", "i");

        MiniMessage.registerTag((isCloseTag, message, args, output) -> {
            final ColoredComponent coloredComponent = new ColoredComponent(message);
            coloredComponent.setObfuscated(!isCloseTag);
            output.add(coloredComponent);
        }, "obfuscated", "o");
    
        MiniMessage.registerTag((isCloseTag, message, args, output) -> {
            final ColoredComponent coloredComponent = new ColoredComponent(message);
            coloredComponent.setStrikethrough(!isCloseTag);
            output.add(coloredComponent);
        }, "strikethrough", "s");

        MiniMessage.registerTag((isCloseTag, message, args, output) -> {
            final ColoredComponent coloredComponent = new ColoredComponent(message);
            coloredComponent.setUnderlined(!isCloseTag);
            output.add(coloredComponent);
        }, "underlined", "u");
    }
}
