package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PacketOutSetBorderLerpSize implements PacketOutbound {

    private final double oldDiameter;
    private final double newDiameter;
    private final int speed;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(DataSize.DOUBLE * 2 + DataSize.INT);
        buffer.writeDouble(oldDiameter);
        buffer.writeDouble(newDiameter);
        buffer.writeVarInt(speed);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_SET_BORDER_LERP_SIZE;
    }
}
