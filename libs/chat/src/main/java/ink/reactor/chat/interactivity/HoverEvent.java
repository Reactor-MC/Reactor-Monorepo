package ink.reactor.chat.interactivity;

import ink.reactor.chat.component.ColoredComponent;
import ink.reactor.chat.component.RawComponent;
import ink.reactor.chat.interactivity.hover.HoverContent;
import ink.reactor.chat.interactivity.hover.ShowEntityContent;
import ink.reactor.chat.interactivity.hover.ShowItemContent;
import ink.reactor.chat.interactivity.hover.ShowTextContent;
import lombok.Getter;

@Getter
public final class HoverEvent {
    private final Action action;
    private final HoverContent content;

    private HoverEvent(Action action, HoverContent content) {
        this.action = action;
        this.content = content;
    }

    public enum Action {
        SHOW_TEXT,
        SHOW_ITEM,
        SHOW_ENTITY;

        private final byte[] id = name().toLowerCase().getBytes();
    
        public byte[] getId() {
            return id;
        }
    }

    public static HoverEvent showEntity(final ShowEntityContent showEntityContent) {
        return new HoverEvent(Action.SHOW_ENTITY, showEntityContent);
    }

    public static HoverEvent showItem(final ShowItemContent showItemContent) {
        return new HoverEvent(Action.SHOW_ITEM, showItemContent);
    }

    public static HoverEvent showText(final ColoredComponent coloredComponent) {
        return new HoverEvent(Action.SHOW_TEXT, new ShowTextContent(coloredComponent));
    }
    
    public static HoverEvent showText(final RawComponent rawComponent) {
        return new HoverEvent(Action.SHOW_TEXT, new ShowTextContent(rawComponent));
    }

    public static HoverEvent showText(final String text) {
        return showText(new RawComponent(text));
    }
}
