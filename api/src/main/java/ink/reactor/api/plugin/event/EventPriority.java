package ink.reactor.api.plugin.event;

public enum EventPriority {
    LOWEST,
    LOW,
    NORMAL,
    HIGH,
    HIGHEST;

    public static final EventPriority[] ALL = values();
}