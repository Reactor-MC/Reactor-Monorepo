package ink.reactor.server.network.handler.login;

import ink.reactor.protocol.ConnectionState;

public final class LoginHandler {

    public static void registerHandlers() {
        ConnectionState.LOGIN.add(new HelloHandler(), new AcknowledgedHandler());
    }
}
