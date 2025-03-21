package ink.reactor.server.plugin.listener.executor;

import java.lang.invoke.MethodHandle;

import ink.reactor.util.event.Cancellable;
import org.tinylog.Logger;

import ink.reactor.api.plugin.listener.EventExecutor;
import ink.reactor.api.plugin.listener.Listener;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListenerReflectionExecutor implements EventExecutor {
    private final Object instance;
    private final Listener listener;
    private final MethodHandle methodHandle;

    @Override
    public void handle(final Object event) {
        if (event instanceof Cancellable cancellable && cancellable.isCancelled() && !listener.ignoreCancelled()) {
            return;
        }
        try {
            methodHandle.invoke(instance, event);
        } catch (final Throwable e) {
            Logger.error(e, "Error executing the listener {}", instance.getClass());
        }
    }
}
