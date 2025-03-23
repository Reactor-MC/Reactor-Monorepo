package ink.reactor.server.network.handler.configuration;

import ink.reactor.protocol.ConnectionState;

public final class ConfigurationHandler {

    public static void registerHandlers() {
        ConnectionState.CONFIGURATION.add(
            new ClientInfoHandler(),
            new ClientKnownPackHandler(),
            new ClientAcknowledgeFinish(),
            new ClientCustomPayloadHandler(),
            new ClientConfigKeepAlive()
        );
    }
}
