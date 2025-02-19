package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutPlayKeepAlive implements PacketOutbound {

    private final long payload;

    public PacketOutPlayKeepAlive(long payload) {
        this.payload = payload;
    }

    @Override
    public byte[] write() {
        System.out.println("PLAY KEPP");
        final ExpectedSizeBuffer expectedSizeBuffer = new ExpectedSizeBuffer(DataSize.LONG);
        expectedSizeBuffer.writeLong(payload);
        return expectedSizeBuffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_KEEP_ALIVE;
    }
}