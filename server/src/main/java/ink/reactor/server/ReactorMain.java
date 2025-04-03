package ink.reactor.server;

import ink.reactor.server.network.SocketServer;

public final class ReactorMain {
    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.bind("localhost", 25565);
    }
}
