package ink.reactor.server.world;

import ink.reactor.api.Reactor;
import ink.reactor.api.player.Player;
import ink.reactor.api.world.World;
import ink.reactor.api.world.chunk.Chunk;
import ink.reactor.api.world.data.Biome;
import ink.reactor.api.world.data.WorldType;
import ink.reactor.protocol.outbound.CachedPacket;
import ink.reactor.protocol.outbound.play.PacketOutUpdateTime;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

public class ReactorWorld extends World {

    public ReactorWorld(Long2ObjectOpenHashMap<Chunk> chunks, String name, WorldType worldType, Biome biome) {
        super(chunks, name, worldType, biome);
    }

    @Override
    public void setTicks(final long ticks) {
        super.setTicks(ticks);

        final PacketOutUpdateTime timePacket = new PacketOutUpdateTime(ticks, ticks % 24000, false);
        final CachedPacket cachedPacket = new CachedPacket(timePacket.write(), timePacket.getId());

        for (final Player player : Reactor.getServer().getPlayers()) {
            player.getConnection().sendPacket(cachedPacket);
        }
    }
}