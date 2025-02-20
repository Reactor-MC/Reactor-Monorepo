package ink.reactor.server.world;

import ink.reactor.api.player.Player;
import ink.reactor.api.world.World;
import ink.reactor.api.world.chunk.Chunk;
import ink.reactor.api.world.data.Biome;
import ink.reactor.api.world.data.WorldType;
import ink.reactor.protocol.outbound.play.PacketOutUpdateTime;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

import java.util.List;

public class ReactorWorld extends World {

    private long ticks;

    public ReactorWorld(Long2ObjectOpenHashMap<Chunk> chunks, String name, WorldType worldType, Biome biome, List<Player> players) {
        super(chunks, name, worldType, biome, players);
    }

    public void setTime(final long time) {
        this.ticks = time;
        for (final Player player : getPlayers()) {
            final PacketOutUpdateTime timePacket = new PacketOutUpdateTime(ticks, ticks % 24000, true);
            player.getConnection().sendPacket(timePacket);
        }
    }
}
