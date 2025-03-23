package ink.reactor.api.config.server;

import ink.reactor.util.DataHolder;

import java.io.File;

public record ServerConfig(
    String ip,
    int port,
    boolean debugMode,
    int pingWaitUpdateTicks,
    Motd defaultMotd,

    File serverFolder,

    Game game,
    Network network,

    DataHolder<String> cachedMotdJson
) {
    public record Game(
        int viewDistance,
        int simulationDistance
    ) {}

    public record Network(
        boolean tcpFastOpen,
        int tcpFastOpenConnections
    ){}
}