package ink.reactor.util.buffer.reader;

import java.util.UUID;

public interface ReadBuffer {

    int readVarInt();
    byte[] readBytes(int length);
    char[] readChars(int length);
    boolean readBoolean();
    byte readByte();
    int readUnsignedByte();
    short readShort();
    char readChar();
    int readInt();
    UUID readUUID();
    long readLong();
    float readFloat();
    double readDouble();
    String readString();

    void skipTo(int index);

    int getIndex();
}