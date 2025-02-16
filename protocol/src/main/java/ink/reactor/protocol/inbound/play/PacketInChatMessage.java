package ink.reactor.protocol.inbound.play;

import java.util.BitSet;

import ink.reactor.protocol.inbound.PacketInData;
import ink.reactor.protocol.inbound.PacketInbound;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public final class PacketInChatMessage implements PacketInbound {

    private String message;
    private long timestamp, salt;
    private int messageCount;
    private ByteBuf signature;
    private BitSet acknowledged;

    @Override
    public void read(final PacketInData data) {
        message = data.readString(256);
        timestamp = data.buffer.readLong();
        salt = data.buffer.readLong();

        boolean haveSignature = data.buffer.readBoolean();
        if (haveSignature) {
            signature = data.buffer.readBytes(256);
        }

        messageCount = data.readVarInt();
        acknowledged = data.readFixedBitSet(20);
    }
}
