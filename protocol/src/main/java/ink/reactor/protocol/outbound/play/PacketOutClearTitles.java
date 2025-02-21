package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PacketOutClearTitles implements PacketOutbound {

    private final boolean reset;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(DataSize.BOOLEAN);
        buffer.writeBoolean(reset);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_CLEAR_TITLES;
    }
}
