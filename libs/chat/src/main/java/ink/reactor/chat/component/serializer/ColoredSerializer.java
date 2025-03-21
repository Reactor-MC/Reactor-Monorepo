package ink.reactor.chat.component.serializer;

import ink.reactor.chat.buffer.ChatBufferWrite;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.ColoredComponent;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ColoredSerializer {

    private static final byte[]
        COLOR = ",\"color\":\"".getBytes(),
        BOLD = ",\"bold\":".getBytes(),
        ITALIC = ",\"italic\":".getBytes(),
        UNDERLINED = ",\"underlined\":".getBytes(),
        OBFUSCATED = ",\"obfuscated\":".getBytes(),
        STRIKETHROUGH = ",\"strikethrough\":".getBytes();

    private static final int
        JSON_OVERHEAD = 11,           // {"text":""}
        COLOR_OVERHEAD = 11,          // ,"color":
        BOLD_OVERHEAD = 8,            // ,"bold":
        ITALIC_OVERHEAD = 10,         // ,"italic":
        UNDERLINED_OVERHEAD = 14,     // ,"underlined":
        OBFUSCATED_OVERHEAD = 14,     // ,"obfuscated":
        STRIKETHROUGH_OVERHEAD = 17;  // ,"strikethrough":

    public static byte[] toJson(final ColoredComponent coloredComponent) {
        final byte[] textBytes = coloredComponent.getText().getBytes();
        final ChatBufferWrite bufferWrite = new ChatBufferWrite(getColoredSize(textBytes, coloredComponent));
        bufferWrite.writeBytes(RawSerializer.JSON_START);
        bufferWrite.writeBytes(textBytes);
        bufferWrite.writeByte('"');

        appendStyle(bufferWrite, coloredComponent);
        bufferWrite.writeByte('}');
        return bufferWrite.getBuffer();
    }

    static int getColoredSize(final byte[] textBytes, final ColoredComponent component) {
        int size = JSON_OVERHEAD + textBytes.length +
            size(component.getBold(), BOLD_OVERHEAD) +
            size(component.getItalic(), ITALIC_OVERHEAD) +
            size(component.getUnderlined(), UNDERLINED_OVERHEAD) +
            size(component.getObfuscated(), OBFUSCATED_OVERHEAD) +
            size(component.getStrikethrough(), STRIKETHROUGH_OVERHEAD);

        if (component.getColor() != null) {
            size += COLOR_OVERHEAD + component.getColor().getCachedName().length;
        }
        return size;
    }

    static int size(final byte value, final int extraOverhead) {
        if (value == ChatComponent.FALSE) {
            return 5 + extraOverhead; // "false" + extra
        }
        if (value == ChatComponent.TRUE) {
            return 4 + extraOverhead; // "true" + extra
        }
        return 0;
    }

    static void appendStyle(final ChatBufferWrite buffer, final ColoredComponent component) {
        if (component.getColor() != null) {
            buffer.writeBytes(COLOR);
            buffer.writeBytes(component.getColor().getCachedName());
            buffer.writeByte('"');
        }
        add(buffer, BOLD, component.getBold());
        add(buffer, ITALIC, component.getItalic());
        add(buffer, UNDERLINED, component.getUnderlined());
        add(buffer, OBFUSCATED, component.getObfuscated());
        add(buffer, STRIKETHROUGH, component.getStrikethrough());
    }

    private static void add(final ChatBufferWrite buffer, final byte[] key, final byte value) {
        if (value != ChatComponent.UNDEFINED) {
            buffer.writeBytes(key);
            buffer.writeBoolean(value == ChatComponent.TRUE);
        }
    }
}