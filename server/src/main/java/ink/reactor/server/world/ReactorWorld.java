package ink.reactor.server.world;

import ink.reactor.api.Reactor;
import ink.reactor.api.player.Player;
import ink.reactor.world.World;
import ink.reactor.world.chunk.Chunk;
import ink.reactor.protocol.outbound.CachedPacket;
import ink.reactor.protocol.outbound.play.PacketOutUpdateTime;
import ink.reactor.world.data.DimensionType;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

public class ReactorWorld extends World {

    public ReactorWorld(Long2ObjectOpenHashMap<Chunk> chunks, String name, DimensionType dimensionType) {
        super(chunks, name, dimensionType);
    }

    public ReactorWorld(String name, DimensionType dimensionType) {
        super(new Long2ObjectOpenHashMap<>(), name, dimensionType);
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