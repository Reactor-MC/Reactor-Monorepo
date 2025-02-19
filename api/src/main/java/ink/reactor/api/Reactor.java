package ink.reactor.api;

import lombok.Getter;

public final class Reactor {
    @Getter
    private static ReactorServer server = null;

    public static void setServer(final ReactorServer server) {
        if (Reactor.server != null) {
            throw new IllegalStateException("Server is already started");
        }
        Reactor.server = server;
    }
}