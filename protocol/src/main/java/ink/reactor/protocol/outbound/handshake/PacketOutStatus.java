package ink.reactor.protocol.outbound.handshake;

import java.nio.charset.StandardCharsets;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutStatus implements PacketOutbound {

    private String json;

    public PacketOutStatus(final String json) {
        this.json = json;
    }

    @Override
    public byte[] write() {
        final byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        final ExpectedSizeBuffer expectedSizeBuffer = new ExpectedSizeBuffer(bytes.length + DataSize.varInt(bytes.length));
        expectedSizeBuffer.writeVarInt(bytes.length);
        expectedSizeBuffer.writeBytes(bytes);
        return expectedSizeBuffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.HANDSHAKE_STATUS;
    }
}
