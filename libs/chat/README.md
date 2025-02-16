# Reactor - Atom Chat

## Faster chat alternative to adventure 

## Features:
1) DON'T USE GSON! Faster json serialization
2) Support legacy format
3) Support hover and click events
4) Support color and style
5) Extensive api for create componentes
6) Support for cymk, rgb and hsl formats
7) Support to nbt system
8) Support to minimessage

And more!

## Example of usage:
```java
    // Minor changes: In 1.8 if you format, example: &bTest&l&aOne. The "One" keyword don't contains style (bold, italic, etc).
    // In this software, this bug is fixed :)
    final ChatComponent[] components = ChatLegacy.format(
    """
        &bReactor Atom &l&7- Chat and nbt library 
        &a#NoMoreGson :)
    """);

    // MiniMessage support
    final List<ChatComponent> minimessageComponents = MiniMessage.format("<color:#999>Hi!");

    // Create your custom tags!
    MiniMessage.registerTag((isCloseTag, message, args, output) -> {
        if (isCloseTag) {
            output.add(new RawComponent("No tag found, lol"));
            return;
        }
        output.add(new ColoredComponent(message, ChatColor.PINK));
    }, "lol", "idk", "prefix");

    ComponentCombiner.toJson(components);      // Combine components with format: {"text":"one","extra":[{"text":"two"}]}
    ComponentCombiner.toJsonArray(components); // Format: [{"text":"one"},{"text":"two"}]
    ComponentCombiner.toNBT(components);

    // Classic way to create components
    final ColoredComponent coloredComponent = new ColoredComponent("test");
    coloredComponent.setColor(ChatColor.rgb(113, 94, 94));

    final FullComponent fullComponent = new FullComponent("Go to github page", ChatColor.YELLOW);
    fullComponent.setClickEvent(ClickEvent.openUrl("https://github.com/Reactor-Minecraft/Atom"));
    fullComponent.setHoverEvent(HoverEvent.showText("Example"));
    fullComponent.setColor(ChatColor.cmyk(1.0f, 0.2f, 0.1f, 0.2f));
    fullComponent.setBold(true);

    // Or you can use a builder
    ComponentBuilder.text("Example of component")
        .onBuild((component) -> System.out.println("A new component is created! " + component.toJson()))
        .setColor(ChatColor.GREEN)
        .setBold(true)
        // Set all styles with .textStyles(bold, italic, underlined, obfuscated, strikethrough)
        .build();
    
    // Supported color formats: hsl, rgb and cymk. 
    ChatColor.AQUA.toHSL();
    ChatColor.gray(123); // Create color with gray scale
```