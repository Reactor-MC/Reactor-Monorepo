package ink.reactor.api;

public final class Reactor {
    private static ReactorServer server = null;

    public static void setServer(final ReactorServer server) {
        if (Reactor.server != null) {
            throw new IllegalStateException("Server is already started");
        }
        Reactor.server = server;
    }

    public static ReactorServer getServer() {
        return server;
    }
}