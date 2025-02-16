package ink.reactor.api.player.connection;

import java.util.UUID;
import ink.reactor.api.player.Player;

public final class ProtocolConnector {

    private static ProtocolPlayerCreator playerCreator;

    public static void setPlayerCreator(ProtocolPlayerCreator playerCreator) {
        if (ProtocolConnector.playerCreator != null) {
            throw new IllegalStateException("Only the server can set player creator");
        }
        ProtocolConnector.playerCreator = playerCreator;
    }

    public static ProtocolPlayerCreator getPlayerCreator() {
        return playerCreator;
    }

    public static interface ProtocolPlayerCreator {
        Player create(final String name, final UUID uuid, final PlayerConnection connection);
    }
}
