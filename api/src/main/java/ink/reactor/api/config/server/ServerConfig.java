package ink.reactor.api.config.server;

public record ServerConfig(
    String ip,
    int port,
    String motd,
    int viewDistance,
    int simulationDistance,
    int networkCompressionThreshold,
    int tcpFastOpenConnections,
    boolean debugMode,
    boolean tcpFastOpen,
    int pingWaitUpdateTicks
) {}