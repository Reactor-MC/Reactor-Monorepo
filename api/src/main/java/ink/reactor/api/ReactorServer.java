package ink.reactor.api;

import ink.reactor.api.config.server.ServerConfig;
import ink.reactor.api.plugin.PluginManager;
import ink.reactor.api.scheduler.ServerScheduler;
import ink.reactor.api.world.WorldManager;

public interface ReactorServer {
    ServerConfig getConfig();
    PluginManager getPluginManager();
    WorldManager getWorldManager();
    ServerScheduler getScheduler();

    String getVersionName();
    int getVersionProtocol();

    void stop();
}