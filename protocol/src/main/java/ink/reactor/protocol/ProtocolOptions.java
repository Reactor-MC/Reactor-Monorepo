package ink.reactor.protocol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProtocolOptions {
    public static final ProtocolOptions OPTIONS = new ProtocolOptions();

    private int simulationDistance = 8;
    private int viewDistance = 10;
    private String defaultMotdJson;

    private int tcpFastOpenConnections = 1;
    private boolean tcpFastOpen = true;
}