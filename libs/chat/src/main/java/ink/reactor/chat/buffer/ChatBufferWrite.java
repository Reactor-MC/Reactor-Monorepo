package ink.reactor.chat.buffer;

public final class ChatBufferWrite {

    private final byte[] buffer;
    private int index;

    public ChatBufferWrite(int initialSize) {
        this.buffer = new byte[initialSize];
    }

    public void writeBytes(final byte[] bytes) {
        System.arraycopy(bytes, 0, buffer, index, bytes.length);
        index += bytes.length;
    }

    public void writeString(final String string) {
        buffer[index++] = '"';
        writeBytes(string.getBytes());
        buffer[index++] = '"';
    }

    public void writeBoolean(final boolean v) {
        if (v) {
            buffer[index++] = 't';
            buffer[index++] = 'r';
            buffer[index++] = 'u';
            buffer[index++] = 'e';
            return;
        }
        buffer[index++] = 'f';
        buffer[index++] = 'a';
        buffer[index++] = 'l';
        buffer[index++] = 's';
        buffer[index++] = 'e';
    }

    public void writeByte(final byte v) {
        buffer[index++] = v;
    }
    public void writeByte(final char v) {
        buffer[index++] = (byte)v;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public int getIndex() {
        return index;
    }
}
