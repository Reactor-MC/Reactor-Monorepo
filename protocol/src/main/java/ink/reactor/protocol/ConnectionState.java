package ink.reactor.protocol;

public enum ConnectionState {

    HANDSHAKE(2),
    LOGIN(6),
    CONFIGURATION(17),
    PLAY(130);

    ConnectionState(int expectedPackets) {

    }
}
