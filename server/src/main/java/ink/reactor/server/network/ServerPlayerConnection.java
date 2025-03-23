package ink.reactor.server.network;

record ServerPlayerConnection(
        PlayerConnectionImpl connection,
        KeepAliveManager keepAliveManager
) { }