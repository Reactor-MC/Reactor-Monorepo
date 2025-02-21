package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PacketOutSetTitleAnimationTimes implements PacketOutbound {

    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(DataSize.INT * 3);
        buffer.writeInt(fadeIn);
        buffer.writeInt(stay);
        buffer.writeInt(fadeOut);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_SET_TITLES_ANIMATION;
    }
}
