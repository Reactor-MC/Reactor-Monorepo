package ink.reactor.chat.component.serializer;

import ink.reactor.chat.buffer.ChatBufferWrite;

final class RawSerializer {
    
    static final byte[] JSON_START = "{\"text\":\"".getBytes();
    static final int JSON_OVERHEAD = 11; // {"text":""}

    static final byte[] toJson(final String text) {
        final byte[] textBytes = text.getBytes();
        final ChatBufferWrite buffer = new ChatBufferWrite(JSON_OVERHEAD + textBytes.length);

        buffer.writeBytes(JSON_START);
        buffer.writeBytes(textBytes);
        buffer.writeByte('"');
        buffer.writeByte('}');

        return buffer.getBuffer();
    }
}