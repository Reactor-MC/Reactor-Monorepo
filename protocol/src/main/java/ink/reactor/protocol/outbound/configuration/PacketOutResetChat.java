package ink.reactor.protocol.outbound.configuration;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;

public final class PacketOutResetChat implements PacketOutbound {
    public static final PacketOutResetChat INSTANCE = new PacketOutResetChat();

    @Override
    public byte[] write() {
        return ExpectedSizeBuffer.EMPTY_BUFFER;
    }
    @Override
    public int getId() {
        return OutProtocol.CONFIGURATION_RESET_CHAT;
    }
}