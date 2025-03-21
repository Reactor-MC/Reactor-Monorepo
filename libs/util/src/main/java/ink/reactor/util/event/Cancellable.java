package ink.reactor.util.event;

public interface Cancellable {
    boolean isCancelled();
    void setCancelled(final boolean cancelled);
}
