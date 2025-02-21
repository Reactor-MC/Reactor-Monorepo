package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PacketOutSetBorderWarningDistance implements PacketOutbound {

    private final int warningBlock;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(DataSize.INT);
        buffer.writeVarInt(warningBlock);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_SET_BORDER_WARNING_DISTANCE;
    }
}
