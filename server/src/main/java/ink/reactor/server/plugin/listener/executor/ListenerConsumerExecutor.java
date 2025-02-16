package ink.reactor.server.plugin.listener.executor;

import java.util.function.Consumer;

import ink.reactor.api.plugin.event.Cancellable;
import ink.reactor.api.plugin.listener.EventExecutor;
import ink.reactor.api.plugin.listener.Listener;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListenerConsumerExecutor<T> implements EventExecutor {
    private final Listener listener;
    private final Consumer<T> consumer;

    @Override
    @SuppressWarnings("unchecked")
    public void handle(final Object event) {
        if (event instanceof Cancellable cancellable && cancellable.isCancelled() && !listener.ignoreCancelled()) {
            return;
        }
        consumer.accept((T) event);
    }
}