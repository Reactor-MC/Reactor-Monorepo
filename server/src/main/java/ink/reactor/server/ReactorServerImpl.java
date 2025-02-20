package ink.reactor.server;

import ink.reactor.api.player.Player;
import ink.reactor.server.player.ReactorPlayer;
import ink.reactor.server.world.ReactorWorld;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
final class ReactorServerImpl implements ReactorServer {

    private final ServerConfig config;
    private final MainThread mainThread;

    private final Console console;
    private final PluginManager pluginManager = new PluginManagerImpl();

    private final WorldManager worldManager = WorldManager.createWorldManager(
        VanillaChunkBuilder::new,
        new ReactorWorld(new Long2ObjectOpenHashMap<>(), "default", WorldType.OVERWORLD, Biome.PLAINS));

    private final Map<UUID, ReactorPlayer> playersByUUID = new Object2ObjectOpenHashMap<>();
    private final Collection<Player> players = new ArrayList<>();

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