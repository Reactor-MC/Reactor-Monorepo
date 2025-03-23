package ink.reactor.protocol;

import java.util.UUID;
import java.util.function.Consumer;

import ink.reactor.api.player.Player;
import ink.reactor.api.player.connection.PlayerConnection;
import lombok.Getter;

public final class ProtocolConnector {

    @Getter
    private static ProtocolPlayerCreator playerCreator;
    @Getter
    private static Consumer<Player> playerCleanup;

    public static void setConnector(ProtocolPlayerCreator playerCreator, Consumer<Player> playerCleanup) {
        if (ProtocolConnector.playerCreator != null) {
            throw new IllegalStateException("Only the server can set player creator");
        }
        ProtocolConnector.playerCreator = playerCreator;
        ProtocolConnector.playerCleanup = playerCleanup;
    }

    public interface ProtocolPlayerCreator {
        Player create(final String name, final UUID uuid, final PlayerConnection connection);
    }
}