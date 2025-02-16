package ink.reactor.protocol.outbound.play;

import ink.reactor.api.player.Player;
import ink.reactor.api.world.World;
import ink.reactor.util.buffer.writer.FriendlyBuffer;

final class PlayStateUtils {

    static void writeSpawnInfo(final FriendlyBuffer buffer, final World world, final Player player) {
   
        buffer.writeVarInt(world.getWorldType().id());
        buffer.writeString("minecraft:" + world.getWorldType().name());
        buffer.writeLong(Long.MAX_VALUE); // Hashed seed
        buffer.writeByte(player.getGamemode().ordinal());
        buffer.writeByte(-1); // Previous Game mode
        buffer.writeBoolean(false); // Is Debug
        buffer.writeBoolean(false); // Is Flat
        buffer.writeBoolean(false); // Has death location	
        buffer.writeVarInt(world.getGamerule().getPortalCooldown());
        buffer.writeVarInt(0); // Sea level
    }
}
