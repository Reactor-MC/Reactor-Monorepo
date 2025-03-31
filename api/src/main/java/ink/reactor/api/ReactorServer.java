package ink.reactor.api;

import ink.reactor.api.config.server.ServerConfig;
import ink.reactor.api.player.Player;
import ink.reactor.api.plugin.PluginManager;
import ink.reactor.api.scheduler.ServerScheduler;
import ink.reactor.command.CommandSender;
import ink.reactor.command.storage.CommandStorage;
import ink.reactor.world.WorldManager;

import java.util.Collection;
import java.util.UUID;

public interface ReactorServer {
    ServerConfig getConfig();
    PluginManager getPluginManager();
    ServerScheduler getScheduler();
    CommandStorage getCommandStorage();
    WorldManager getWorldManager();

    String getVersionName();
    int getVersionProtocol();

    Collection<Player> getPlayers();
    Player getPlayer(final UUID uuid);

    CommandSender getConsole();

    void stop();
}