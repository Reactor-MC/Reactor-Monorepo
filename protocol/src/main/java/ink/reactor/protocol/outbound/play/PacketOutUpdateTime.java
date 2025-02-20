package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.DataSize;
import ink.reactor.util.buffer.writer.ExpectedSizeBuffer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PacketOutUpdateTime implements PacketOutbound {

    private long worldAge;
    private long timeOfDay;
    private boolean timeOfTheDayIncreasing;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(DataSize.LONG * 2 + DataSize.BOOLEAN);
        buffer.writeLong(worldAge);
        buffer.writeLong(timeOfDay);
        buffer.writeBoolean(timeOfTheDayIncreasing);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_SET_TIME;
    }
}
