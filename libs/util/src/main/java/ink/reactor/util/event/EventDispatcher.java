package ink.reactor.util.event;

import java.util.function.Consumer;

import ink.reactor.util.collection.CopyOnWriteCollection;

public final class EventDispatcher {

    private static final CopyOnWriteCollection<Consumer<Object>> eventConsumers = new CopyOnWriteCollection<>(1);

    public static void call(final Object event) {
        if (event != null) {
            for (final Consumer<Object> eventConsumer : eventConsumers) {
                eventConsumer.accept(event);
            }
        }
    }

    public static void addConsumer(Consumer<Object> eventConsumer) {
        eventConsumers.add(eventConsumer);
    }

    public static void removeConsumer(Consumer<Object> eventConsumer) {
        eventConsumers.remove(eventConsumer);
    }
}
