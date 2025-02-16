package ink.reactor.protocol.outbound.login;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutLoginCompression implements PacketOutbound {

    private final int threshold;

    public PacketOutLoginCompression(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer expectedSizeBuffer = new ExpectedSizeBuffer(DataSize.varInt(threshold));
        expectedSizeBuffer.writeVarInt(threshold);

        return expectedSizeBuffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.LOGIN_LOGIN_COMPRESSION;
    }
}
