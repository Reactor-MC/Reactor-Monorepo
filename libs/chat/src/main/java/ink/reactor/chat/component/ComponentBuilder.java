package ink.reactor.chat.component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import ink.reactor.chat.ChatColor;
import ink.reactor.chat.interactivity.ClickEvent;
import ink.reactor.chat.interactivity.HoverEvent;

public final class ComponentBuilder {

    private static final byte
        RAW = 0, COLORED = 1, FULL = 2;

    private byte componentType = RAW;

    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;
    private ChatColor color;
    private byte bold, italic, underlined, obfuscated, strikethrough;
    private String text;

    private List<Consumer<ChatComponent>> buildConsumers;

    public ComponentBuilder(String text) {
        this.text = text;
    }

    public static final ComponentBuilder text(final Object object) {
        return new ComponentBuilder(object.toString());
    }

    public ComponentBuilder onBuild(Consumer<ChatComponent> consumer) {
        if (buildConsumers == null) {
            buildConsumers = new ArrayList<>();
        }
        buildConsumers.add(consumer);
        return this;
    }

    public ComponentBuilder onClick(final ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
        this.componentType = FULL;
        return this;
    }

    public ComponentBuilder onHover(final HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
        this.componentType = FULL;
        return this;
    }

    public ComponentBuilder setColor(final ChatColor color) {
        this.color = color;
        this.componentType = COLORED;
        return this;
    }

    public ComponentBuilder textStyles(final boolean bold, final boolean italic, final boolean underlined, final boolean obfuscated, final boolean strikethrough) {
        setBold(bold);
        setItalic(italic);
        setUnderlined(underlined);
        setObfuscated(obfuscated);
        setStrikethrough(strikethrough);
        return this;
    }

    public ComponentBuilder setBold(final boolean bold) {
        this.bold = bold ? ChatComponent.TRUE : ChatComponent.FALSE;
        this.componentType = COLORED;
        return this;
    }

    public ComponentBuilder setItalic(final boolean italic) {
        this.italic = italic ? ChatComponent.TRUE : ChatComponent.FALSE;
        this.componentType = COLORED;
        return this;
    }

    public ComponentBuilder setUnderlined(final boolean underlined) {
        this.underlined = underlined ? ChatComponent.TRUE : ChatComponent.FALSE;
        this.componentType = COLORED;
        return this;
    }

    public ComponentBuilder setObfuscated(final boolean obfuscated) {
        this.obfuscated = obfuscated ? ChatComponent.TRUE : ChatComponent.FALSE;
        this.componentType = COLORED;
        return this;
    }

    public ComponentBuilder setStrikethrough(final boolean strikethrough) {
        this.strikethrough = strikethrough ? ChatComponent.TRUE : ChatComponent.FALSE;
        this.componentType = COLORED;
        return this;
    }

    public ComponentBuilder setText(final String text) {
        this.text = text;
        return this;
    }

    public ChatComponent build() {
        if (componentType == RAW) {
            return buildRaw();
        }
        if (componentType == COLORED) {
            return buildColored();
        }
        return buildFullComponent();
    }

    public ColoredComponent buildColored() {
        final ColoredComponent coloredComponent = new ColoredComponent(text, color, bold, italic, underlined, obfuscated, strikethrough);
        executeBuildConsumers(coloredComponent);
        return coloredComponent;
    }

    public RawComponent buildRaw() {
        final RawComponent rawComponent = new RawComponent(text);
        executeBuildConsumers(rawComponent);
        return rawComponent;
    }

    public Supplier<ChatComponent> buildCreator() {
        return this::build;
    }

    public FullComponent buildFullComponent() {
        final FullComponent full = new FullComponent(text);

        full.setColor(color);
        full.setHoverEvent(hoverEvent);
        full.setClickEvent(clickEvent);

        if (bold != ChatComponent.UNDEFINED) {
            full.setBold(bold == ChatComponent.TRUE);
        }
        if (italic != ChatComponent.UNDEFINED) {
            full.setItalic(italic == ChatComponent.TRUE);
        }
        if (underlined != ChatComponent.UNDEFINED) {
            full.setUnderlined(underlined == ChatComponent.TRUE);   
        }
        if (obfuscated != ChatComponent.UNDEFINED) {
            full.setObfuscated(obfuscated == ChatComponent.TRUE);
        }
        if (strikethrough != ChatComponent.UNDEFINED) {
            full.setStrikethrough(strikethrough == ChatComponent.TRUE);    
        }

        executeBuildConsumers(full);
        return full;
    }

    private void executeBuildConsumers(final ChatComponent chatComponent) {
        if (buildConsumers != null) {
            for (final Consumer<ChatComponent> consumer : buildConsumers) {
                consumer.accept(chatComponent);
            }
        }
    }
}
