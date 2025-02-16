package ink.reactor.api.plugin;

import ink.reactor.api.Reactor;
import ink.reactor.api.ReactorServer;

public abstract class Plugin {

    public static final byte
        DISABLED = 0,
        LOADED = 1,
        ENABLED = 2;

    private byte state = DISABLED;

    protected void onLoad() {}
    protected void onEnable() {}
    protected void onDisable() {}

    public final boolean load() {
        if (state != DISABLED) {
            return false;
        }
        onLoad();
        state = LOADED;
        return true;
    }

    public final boolean enable() {
        if (state != LOADED) {
            return false;
        }
        onEnable();
        state = ENABLED;
        return true;
    }

    public final boolean disable() {
        if (state != ENABLED) {
            return false;
        }
        onDisable();
        state = DISABLED;
        return true;
    }

    public final int getState() {
        return state;
    }

    public abstract String getName();

    @Override
    public final boolean equals(Object obj) {
        return obj == this || (obj instanceof Plugin otherPlugin && otherPlugin.getName().equals(this.getName()));
    }

    // Shortcuts for write less code 
    public ReactorServer getServer() {
        return Reactor.getServer();
    }
    public PluginManager getPluginManager() {
        return Reactor.getServer().getPluginManager();
    }
}