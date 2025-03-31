package ink.reactor.server;

import ink.reactor.api.player.Player;
import ink.reactor.command.storage.CommandStorage;
import ink.reactor.server.player.ReactorPlayer;
import ink.reactor.server.world.ReactorWorld;
import ink.reactor.world.WorldManager;
import ink.reactor.world.data.DimensionType;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.tinylog.Logger;

import ink.reactor.api.ReactorServer;
import ink.reactor.api.config.server.ServerConfig;
import ink.reactor.api.plugin.PluginManager;
import ink.reactor.api.scheduler.ServerScheduler;
import ink.reactor.server.console.Console;
import ink.reactor.server.plugin.PluginManagerImpl;
import ink.reactor.server.tick.MainThread;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Getter
@RequiredArgsConstructor
final class ReactorServerImpl implements ReactorServer {

    private final ServerConfig config;
    private final MainThread mainThread;

    private final Console console;
    private final PluginManager pluginManager = new PluginManagerImpl();
    private final CommandStorage commandStorage = new CommandStorage();

    private final Map<UUID, ReactorPlayer> playersByUUID = new Object2ObjectOpenHashMap<>();
    private final Collection<Player> players = new ArrayList<>();
    private final WorldManager worldManager = WorldManager.createWorldManager(new ReactorWorld("world", DimensionType.OVERWORLD));

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