package ink.reactor.chat.component.serializer;

import ink.reactor.chat.buffer.ChatBufferWrite;
import ink.reactor.chat.component.FullComponent;
import ink.reactor.chat.interactivity.HoverEvent;

final class FullSerializer {

    private static final byte[]
        CLICK_EVENT_START = ",\"clickEvent\":{\"action\":\"".getBytes(),
        CLICK_EVENT_VALUE = "\",\"value\":".getBytes();
    
    private static final byte[]
        HOVER_START = ",\"hoverEvent\":{\"action\":".getBytes(),
        HOVER_CONTENTS = ",\"contents:\":".getBytes();

    private static final int
        CLICK_EVENT_OVERHEAD = 38, // ,"clickEvent":{"action":"","value":""}
        HOVER_OVERHEAD = 41;       // ,"hoverEvent":{"action":"","contents":{}}

    static final byte[] toJson(final FullComponent full) {
        final byte[] hover = processHoverEvent(full.getHoverEvent());

        final byte[] textBytes = full.getText().getBytes();
        final ChatBufferWrite buffer = new ChatBufferWrite(getSize(full, textBytes, hover));

        buffer.writeBytes(RawSerializer.JSON_START);
        buffer.writeBytes(textBytes);
        buffer.writeByte('"');

        ColoredSerializer.appendStyle(buffer, full);

        if (full.getClickEvent() != null) {
            buffer.writeBytes(CLICK_EVENT_START);
            buffer.writeBytes(full.getClickEvent().action().getId());
            buffer.writeBytes(CLICK_EVENT_VALUE);
            buffer.writeString(full.getClickEvent().value());
            buffer.writeByte('}'); // close click event
        }

        if (hover != null) {
            buffer.writeBytes(hover);
        }

        buffer.writeByte('}');

        return buffer.getBuffer();
    }

    private static byte[] processHoverEvent(final HoverEvent hoverEvent) {
        if (hoverEvent == null) {
            return null;
        }

        final byte[] content = hoverEvent.getContent().toJsonBytes();
        final ChatBufferWrite buffer = new ChatBufferWrite(HOVER_OVERHEAD + content.length + hoverEvent.getAction().getId().length);

        buffer.writeBytes(HOVER_START);
        buffer.writeByte('"');
        buffer.writeBytes(hoverEvent.getAction().getId());
        buffer.writeByte('"');
        buffer.writeBytes(HOVER_CONTENTS);
        buffer.writeBytes(content);
        buffer.writeByte('}');

        return buffer.getBuffer();
    }

    private static int getSize(final FullComponent full, final byte[] textBytes, final byte[] hover) {
        int size = ColoredSerializer.getColoredSize(textBytes, full);

        if (full.getClickEvent() != null) {
            size += CLICK_EVENT_OVERHEAD;
            size += full.getClickEvent().action().getId().length;
            size += full.getClickEvent().value().length();
        }

        if (hover != null) {
            size += hover.length;
        }

        return size;
    }
}
