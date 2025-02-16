package ink.reactor.chat.component.serializer;

import ink.reactor.chat.component.ColoredComponent;
import ink.reactor.chat.component.FullComponent;
import ink.reactor.chat.component.RawComponent;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class JsonComponentSerializer {

    public static final byte[] toJson(final String text) {
        return RawSerializer.toJson(text);
    }

    public static final byte[] toJson(final RawComponent rawComponent) {
        return RawSerializer.toJson(rawComponent.getText());
    }

    public static final byte[] toJson(final ColoredComponent coloredComponent) {
        return ColoredSerializer.toJson(coloredComponent);
    }

    public static final byte[] toJson(final FullComponent fullComponent) {
        return FullSerializer.toJson(fullComponent);
    }
}
