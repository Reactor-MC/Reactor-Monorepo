package ink.reactor.api.config.server;

import java.io.File;

public record ServerConfig(
    String ip,
    int port,
    boolean debugMode,
    int pingWaitUpdateTicks,
    Motd defaultMotd,

    File serverFolder
) {}