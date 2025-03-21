package ink.reactor.chat.component.serializer;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class RawSerializer {
    
    static final byte[] JSON_START = "{\"text\":\"".getBytes();
    static final int JSON_OVERHEAD = 11; // {"text":""}

    public static byte[] toJson(final String text) {
        final byte[] textBytes = text.getBytes();
        final byte[] buffer = new byte[JSON_OVERHEAD + textBytes.length];

        int index = JSON_START.length;
        System.arraycopy(JSON_START, 0, buffer, 0, index);

        buffer[index] = '"';
        System.arraycopy(textBytes, 0, buffer, index, textBytes.length);
        index += textBytes.length;
        buffer[index++] = '"';
        buffer[index] = '}';

        return buffer;
    }
}