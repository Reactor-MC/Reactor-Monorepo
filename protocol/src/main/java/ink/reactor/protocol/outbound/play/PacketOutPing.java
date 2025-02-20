package ink.reactor.protocol.outbound.play;

import ink.reactor.protocol.outbound.CachedPacket;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutPing {

    public static final int PING_ID = 777_777;

    public static final CachedPacket INSTANCE = new CachedPacket(
        write(),
        OutProtocol.PLAY_PING
    );

    private static byte[] write() {
        final ExpectedSizeBuffer expectedSizeBuffer = new ExpectedSizeBuffer(DataSize.INT);
        expectedSizeBuffer.writeInt(PING_ID);
        return expectedSizeBuffer.buffer;
    }
}