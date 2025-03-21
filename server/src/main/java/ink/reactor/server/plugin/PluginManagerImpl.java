package ink.reactor.server.plugin;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.tinylog.Logger;

import ink.reactor.api.plugin.Plugin;
import ink.reactor.api.plugin.PluginManager;
import ink.reactor.api.plugin.event.EventPriority;
import ink.reactor.api.plugin.listener.EventExecutor;
import ink.reactor.api.plugin.listener.Listener;
import ink.reactor.server.plugin.listener.ListenerLoader;
import ink.reactor.server.plugin.listener.ListenerManager;
import ink.reactor.server.plugin.listener.RegisteredListener;
import ink.reactor.server.plugin.listener.executor.ListenerConsumerExecutor;
import ink.reactor.util.event.EventDispatcher;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

public final class PluginManagerImpl implements PluginManager {

    private final Set<Plugin> plugins = new ObjectOpenHashSet<>();
    private final Map<Class<?>, ListenerManager> listeners = new Object2ObjectOpenHashMap<>();
    private final Map<Class<?>, Object> sharedData = new Object2ObjectOpenHashMap<>();

    public PluginManagerImpl() {
        EventDispatcher.addConsumer(this::callEvent);
    }

    @Override
    public void registerListener(final Plugin plugin, final Object... listeners) {
        for (final Object listener : listeners) {
            addListeners(ListenerLoader.load(listener, plugin, null));
        }
    }

    @Override
    public void registerListener(final Plugin plugin, final EventExecutor executor, final Object... listeners) {
        for (final Object listener : listeners) {
            addListeners(ListenerLoader.load(listener, plugin, executor));
        }
    }

    @Override
    public <T> void registerListener(
        final Plugin plugin, final EventExecutor executor, final EventPriority priority,
        final boolean ignoreCancelled, final Class<T> clazz, final Consumer<T> consumer
    ) {
        final Listener listener = new Listener() {
            public Class<? extends Annotation> annotationType() {
                return Listener.class;
            }
            public boolean ignoreCancelled() {
                return ignoreCancelled;
            }
            public EventPriority priority() {
                return priority;
            }
        };
        addListeners(List.of(new RegisteredListener(clazz, plugin, listener, executor, clazz)));
    }

    @Override
    public <T> void setData(Class<T> dataClass, T data) {
        this.sharedData.put(dataClass, data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getData(Class<T> dataClass) {
        return (T)this.sharedData.get(dataClass);
    }

    @Override
    public <T> void registerListener(final Plugin plugin, final Class<T> eventClass, final Consumer<T> consumer) {
        final Listener listener = new Listener() {
            public Class<? extends Annotation> annotationType() {
                return Listener.class;
            }
            public boolean ignoreCancelled() {
                return false;
            }
            public EventPriority priority() {
                return EventPriority.NORMAL;
            }
        };
        addListeners(List.of(new RegisteredListener(eventClass, plugin, listener, new ListenerConsumerExecutor<>(listener, consumer), eventClass)));
    }

    private void addListeners(final List<RegisteredListener> listListeners) {
        if (listListeners == null) {
            return;
        }
        for (final RegisteredListener listener : listListeners) {
            ListenerManager manager = listeners.get(listener.eventClass());
            if (manager == null) {
                manager = new ListenerManager();
                listeners.put(listener.eventClass(), manager);
            }
            manager.addListener(listener);
        }
    }

    @Override
    public void unloadListener(final Object listener) {
        final List<Class<?>> eventTypes = ListenerLoader.getEventTypes(listener);
        if (eventTypes == null) {
            return;
        }
        for (final Class<?> eventType : eventTypes) {
            final ListenerManager listenerManager = listeners.get(eventType);
            listenerManager.removeListener(null);
        }
    }

    @Override
    public void unloadListeners(Plugin plugin) {
        final Collection<ListenerManager> managers = listeners.values();
        for (final ListenerManager manager : managers) {
            manager.removeListeners(plugin);
        }
    }

    @Override
    public void callEvent(final Object event) {
        final ListenerManager listenersManager = listeners.get(event.getClass());
        if (listenersManager != null) {
            listenersManager.execute(event);
        }
    }

    @Override
    public void unloadPlugins() {
        for (final Plugin plugin : plugins) {
            if (plugin.disable()) {
                Logger.info("Plugin " + plugin.getName() + " disabled");
            }
        }
        plugins.clear();
    }

    @Override
    public void enablePlugins() {
        for (final Plugin plugin : plugins) {
            if (plugin.enable()) {
                Logger.info("Plugin " + plugin.getName() + " enabled");
            }
        }
    }

    @Override
    public void loadPlugins(final File directory) {
        if (directory.exists()) {
            plugins.addAll(PluginLoader.load(directory));       
        }
    }

    @Override
    public Plugin getPlugin(String name) {
        for (final Plugin plugin : plugins) {
            if (plugin.getName().equals(name)) {
                return plugin;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Plugin> T getPlugin(Class<T> pluginClass) {
        for (final Plugin plugin : plugins) {
            if (plugin.getClass().equals(pluginClass)) {
                return (T) plugin;
            }
        }
        return null;
    }

    @Override
    public Collection<Plugin> getPlugins() {
        return plugins;
    }
}