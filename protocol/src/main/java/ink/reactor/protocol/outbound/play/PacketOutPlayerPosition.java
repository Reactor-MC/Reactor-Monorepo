package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.Player;
import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutPlayerPosition implements PacketOutbound {
    private final Player player;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer buffer = new ExpectedSizeBuffer(
            DataSize.varInt(player.getId()) +
            DataSize.DOUBLE * 6 +
            DataSize.FLOAT * 2 +
            DataSize.BYTE
        );

        buffer.writeVarInt(player.getId());
        buffer.writeDouble(player.getX());
        buffer.writeDouble(player.getY());
        buffer.writeDouble(player.getZ());
        buffer.writeDouble(0); // Velocity x
        buffer.writeDouble(0); // Velocity y
        buffer.writeDouble(0); // Velocity z
        buffer.writeFloat(player.getYaw());
        buffer.writeFloat(player.getPitch());
        buffer.writeInt(0); // Teleport flags

        return buffer.buffer;
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_PLAYER_POSITION;
    }
}