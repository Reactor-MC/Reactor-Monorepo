package ink.reactor.protocol.outbound.configuration;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutPluginMessage implements PacketOutbound {

    private final String channel;
    private final byte[] data;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer eBuffer = new ExpectedSizeBuffer(DataSize.string(channel) + DataSize.prefixedBytes(data));
        eBuffer.writeString(channel);
        eBuffer.writeVarInt(data.length);
        eBuffer.writeBytes(data);
        return eBuffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.CONFIGURATION_CUSTOM_PAYLOAD;
    }
}