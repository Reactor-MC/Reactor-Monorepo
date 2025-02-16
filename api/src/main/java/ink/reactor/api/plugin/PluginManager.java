package ink.reactor.api.plugin;

import java.io.File;
import java.util.Collection;
import java.util.function.Consumer;

import ink.reactor.api.plugin.event.EventPriority;
import ink.reactor.api.plugin.listener.EventExecutor;

public interface PluginManager {
    void registerListener(final Plugin plugin, final Object... listeners);
    void registerListener(final Plugin plugin, final EventExecutor executor, final Object... listeners);

    <T> void registerListener(final Plugin plugin, final EventExecutor executor, final EventPriority priority, final boolean ignoreCancelled, final Class<T> eventClass, final Consumer<T> consumer);
    <T> void registerListener(final Plugin plugin, final Class<T> eventClass, final Consumer<T> consumer);

    <T> void setData(final Class<T> dataClass, final T data);
    <T> T getData(final Class<T> dataClass);

    void unloadListener(final Object listener);
    void unloadListeners(final Plugin plugin);

    void unloadPlugins();
    void enablePlugins();

    void loadPlugins(final File directory);
    void callEvent(final Object event);

    Plugin getPlugin(final String name);
    <T extends Plugin> T getPlugin(final Class<T> pluginClass);

    Collection<Plugin> getPlugins();
}