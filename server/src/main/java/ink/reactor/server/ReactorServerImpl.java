package ink.reactor.server;

import org.tinylog.Logger;

import ink.reactor.api.ReactorServer;
import ink.reactor.api.config.server.ServerConfig;
import ink.reactor.api.plugin.PluginManager;
import ink.reactor.api.scheduler.ServerScheduler;
import ink.reactor.api.world.World;
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

@Getter
@RequiredArgsConstructor
final class ReactorServerImpl implements ReactorServer {

    private final ServerConfig config;
    private final MainThread mainThread;

    private final Console console;
    private final PluginManager pluginManager = new PluginManagerImpl();

    private final WorldManager worldManager = new WorldManager(
        VanillaChunkBuilder::new,
        new World(new Long2ObjectOpenHashMap<>(), "default", WorldType.OVERWORLD, Biome.PLAINS));

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
}