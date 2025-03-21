package ink.reactor.server;

import ink.reactor.api.player.Player;
import ink.reactor.command.storage.CommandStorage;
import ink.reactor.nbt.type.NBTGeneral;
import ink.reactor.nbt.writer.NBTStreamWriter;
import ink.reactor.server.player.ReactorPlayer;
import ink.reactor.server.world.ReactorWorld;
import ink.reactor.world.chunk.vanilla.array.VanillaChunk;
import ink.reactor.world.chunk.vanilla.array.VanillaChunkSerializer;
import ink.reactor.world.chunk.vanilla.array.VanillaHeightMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.tinylog.Logger;

import ink.reactor.api.ReactorServer;
import ink.reactor.api.config.server.ServerConfig;
import ink.reactor.api.plugin.PluginManager;
import ink.reactor.api.scheduler.ServerScheduler;
import ink.reactor.api.world.WorldManager;
import ink.reactor.api.world.data.Biome;
import ink.reactor.api.world.data.WorldType;
import ink.reactor.server.console.Console;
import ink.reactor.server.plugin.PluginManagerImpl;
import ink.reactor.server.tick.MainThread;
import ink.reactor.server.world.chunk.VanillaChunkBuilder;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Getter
@RequiredArgsConstructor
final class ReactorServerImpl implements ReactorServer {

    private final ServerConfig config;
    private final MainThread mainThread;

    private final Console console;
    private final PluginManager pluginManager = new PluginManagerImpl();
    private final CommandStorage commandStorage = new CommandStorage();

    private final WorldManager worldManager = WorldManager.createWorldManager(
        VanillaChunkBuilder::new,
        new ReactorWorld(new Long2ObjectOpenHashMap<>(), "default", WorldType.OVERWORLD, Biome.PLAINS));

    private final Map<UUID, ReactorPlayer> playersByUUID = new Object2ObjectOpenHashMap<>();
    private final Collection<Player> players = new ArrayList<>();

/*
    public static void main(String[] args) throws IOException {
        final VanillaChunk a = VanillaChunk.of(0,0, ink.reactor.world.data.WorldType.OVERWORLD);
        a.setBlock(0,-1,0,'a');
        a.setBlock(0,6,1,'b');
        final long[] data = a.getHeightMap().writeHeightmap();
        final NBTGeneral heightmaps = new NBTGeneral();
        heightmaps.addLongArray("MOTION_BLOCKING", data);
        heightmaps.addLongArray("WORLD_SURFACE", data);
        NBTStreamWriter.writeFile(Path.of("/home/choco/Desktop/fsdf"), heightmaps);
    }
 */

    @Override
    public ServerScheduler getScheduler() {
        return mainThread.getScheduler();
    }

    @Override
    public void stop() {
        Logger.info("Stopping server...");
        System.exit(0);
    }

    void onExit() {
        mainThread.stop(console::stop);
    }

    @Override
    public String getVersionName() {
        return "1.21.4";
    }

    @Override
    public int getVersionProtocol() {
        return 769;
    }

    @Override
    public Player getPlayer(final UUID uuid) {
        return playersByUUID.get(uuid);
    }
}