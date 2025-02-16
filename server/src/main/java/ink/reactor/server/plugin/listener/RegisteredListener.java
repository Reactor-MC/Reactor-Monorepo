package ink.reactor.server.plugin.listener;

import ink.reactor.api.plugin.Plugin;
import ink.reactor.api.plugin.listener.EventExecutor;
import ink.reactor.api.plugin.listener.Listener;

public record RegisteredListener(
    Class<?> sourceClass,
    Plugin plugin,
    Listener listener,
    EventExecutor eventExecutor,
    Class<?> eventClass
) {}