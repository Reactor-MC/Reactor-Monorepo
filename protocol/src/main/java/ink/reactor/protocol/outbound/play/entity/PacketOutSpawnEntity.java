package ink.reactor.protocol.outbound.play.entity;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.entity.Entity;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutSpawnEntity implements PacketOutbound {

    private final Entity entity;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(
            DataSize.varInt(entity.getId()) +
            DataSize.UUID
        );
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_ADD_ENTITY;
    }
}