package ink.reactor.protocol.inbound;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import ink.reactor.util.MathUtil;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;

public final class PacketInData {
    public final ByteBuf buffer;

    public PacketInData(ByteBuf buffer) {
        this.buffer = buffer;
    }

    public int readVarInt() {
        byte b0;
        int i = 0;
        int j = 0;
        do {
            b0 = buffer.readByte();
            i |= (b0 & Byte.MAX_VALUE) << j++ * 7;
            if (j > 5)
                throw new RuntimeException("VarInt too big");
        } while ((b0 & 0x80) == 128);
        return i;
    }

    public String readString() {
        return readString(Short.MAX_VALUE);
    }

    public String readString(final int maxLength) {
        final int stringLength = this.readVarInt();
        if (stringLength > maxLength * 4) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + stringLength + " > " + (stringLength * 4) + ")");
        }
        if (stringLength < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        }

        final String string = buffer.toString(buffer.readerIndex(), stringLength, StandardCharsets.UTF_8);
        buffer.readerIndex(buffer.readerIndex() + stringLength);

        if (string.length() > maxLength) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + stringLength + " > " + stringLength + ")");
        }

        return string;
    }

    public BitSet readFixedBitSet(final int a) {
        final byte[] abyte = new byte[MathUtil.positiveCeilDiv(a, 8)];
        buffer.readBytes(abyte);
        return BitSet.valueOf(abyte);
    }
}
