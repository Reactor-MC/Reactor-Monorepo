package ink.reactor.debug;

import ink.reactor.api.plugin.Plugin;
import ink.reactor.debug.listener.PlayerJoinListener;

public final class DebugPlugin extends Plugin {

    @Override
    protected void onEnable() {
        getPluginManager().registerListener(this, new PlayerJoinListener());
    }

    @Override
    public String getName() {
        return "debug";
    }
}
