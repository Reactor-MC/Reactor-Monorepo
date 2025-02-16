package ink.reactor.server.plugin.listener;

import java.util.ArrayList;
import java.util.Collection;

import ink.reactor.api.plugin.Plugin;
import ink.reactor.api.plugin.event.EventPriority;

public final class ListenerManager {

    @SuppressWarnings("unchecked")
    private final Collection<RegisteredListener>[] priorities = new Collection[EventPriority.ALL.length];

    public void execute(final Object event) {
        for (final Collection<RegisteredListener> priority : priorities) {
            if (priority == null) {
                continue;
            }
            for (final RegisteredListener listener : priority) {
                listener.eventExecutor().handle(event);
            }
        }
    }

    public boolean removeListener(final Class<?> listenerClass) {
        for (final EventPriority priority : EventPriority.ALL) {
            final Collection<RegisteredListener> collection = priorities[priority.ordinal()];
            if (collection != null) {
                collection.removeIf((registeredListener) -> registeredListener.sourceClass() == listenerClass);
            }
        }
        return false;
    }

    public void removeListeners(final Plugin plugin) {
        for (final EventPriority priority : EventPriority.ALL) {
            final Collection<RegisteredListener> collection = priorities[priority.ordinal()];
            if (collection != null) {
                collection.removeIf((registeredListener) -> registeredListener.plugin().equals(plugin));
            }
        }
    }

    public void addListener(final RegisteredListener registeredListener) {
        final int ordinal = registeredListener.listener().priority().ordinal();
        Collection<RegisteredListener> listeners = priorities[ordinal];
        if (listeners == null) {
            listeners = new ArrayList<>(1);
            priorities[ordinal] = listeners;
        }
        listeners.add(registeredListener);
    }
}