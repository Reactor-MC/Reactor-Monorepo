package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.Player;
import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.api.world.World;
import ink.reactor.api.world.data.Gamerule;
import ink.reactor.api.world.data.WorldType;
import ink.reactor.protocol.outbound.OutProtocol;
import ink.reactor.util.buffer.writer.FriendlyBuffer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutLoginPlay implements PacketOutbound {

    private final PlayerConnection connection;
    private final World world;

    private final int viewDistance;
    private final int simulationDistance;

    @Override
    public byte[] write() {
        final Gamerule gamerule = world.getGamerule();

        final Player player = connection.getPlayer();
        final FriendlyBuffer buffer = new FriendlyBuffer(50);

        buffer.writeInt(player.getId());
        buffer.writeBoolean(gamerule.isHardCore());
        buffer.writeVarInt(WorldType.ALL.length);
        for (final WorldType worldType : WorldType.ALL) {
            buffer.writeString(worldType.name());
        }
        buffer.writeVarInt(30);
        buffer.writeVarInt(viewDistance);
        buffer.writeVarInt(simulationDistance);
        buffer.writeBoolean(false); // Reduced Debug Info
        buffer.writeBoolean(gamerule.isRespawnScreen());
        buffer.writeBoolean(false); // Do limited crafting
        PlayStateUtils.writeSpawnInfo(buffer, world, player);
        buffer.writeBoolean(false);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return OutProtocol.PLAY_LOGIN;
    }
}