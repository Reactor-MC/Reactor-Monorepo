package ink.reactor.api.plugin;

import ink.reactor.api.Reactor;
import ink.reactor.api.ReactorServer;
import org.tinylog.Logger;

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
        try {
            onLoad();
            state = LOADED;
            return true;
        } catch (final Exception e) {
            Logger.error("An exception occurred when trying to disable the plugin " + getName(), e);
            return false;
        }
    }

    public final boolean enable() {
        if (state != LOADED) {
            return false;
        }
        try {
            onEnable();
            state = ENABLED;
            return true;
        } catch (final Exception e) {
            Logger.error("An exception occurred when trying to enable the plugin " + getName(), e);
            return false;
        }
    }

    public final boolean disable() {
        if (state != ENABLED) {
            return false;
        }
        try {
            onDisable();
            state = DISABLED;
            return true;
        } catch (final Exception e) {
            Logger.error("An exception occurred when trying to disable the plugin " + getName(), e);
            return false;
        }
    }

    public final boolean loadAndEnable() {
        if (state == LOADED) {
            return enable();
        }
        return load() && enable();
    }

    public final int getState() {
        return state;
    }

    public final String getStateName() {
        return switch (state) {
            case LOADED -> "loaded";
            case ENABLED -> "enabled";
            default -> "disabled";
        };
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