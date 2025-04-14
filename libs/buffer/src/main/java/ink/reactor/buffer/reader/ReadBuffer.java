package ink.reactor.buffer.reader;

import java.util.BitSet;
import java.util.UUID;

public interface ReadBuffer {

    int readVarInt();
    byte[] readBytes(int length);
    char[] readChars(int length);
    long[] readLongArray();
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
    BitSet readBitSet();

    void skipTo(int index);

    int getIndex();
}