package ink.reactor.server.network;

import ink.reactor.api.scheduler.TickUnit;
import ink.reactor.chat.component.RawComponent;
import ink.reactor.protocol.ConnectionState;
import ink.reactor.protocol.outbound.configuration.PacketOutConfigKeepAlive;
import ink.reactor.protocol.outbound.play.PacketOutPlayKeepAlive;
import lombok.Getter;

public final class KeepAliveManager {

    private static final int KEEP_ALIVE_DELAY = TickUnit.SECONDS.toTicks(15);

    private final PlayerConnectionImpl connection;

    @Getter
    private long payload = 0;

    private int countdown = TickUnit.SECONDS.toTicks(5);

    KeepAliveManager(PlayerConnectionImpl playerConnectionImpl) {
        this.connection = playerConnectionImpl;
    }

    public void keepAlive() {
        if (--countdown > 0) {
            return;
        }

        countdown = KEEP_ALIVE_DELAY;
        payload++;

        if (connection.getState() == ConnectionState.PLAY) {
            connection.sendPacket(new PacketOutPlayKeepAlive(payload));
            return;
        }
        if (connection.getState() == ConnectionState.CONFIGURATION) {
            connection.sendPacket(new PacketOutConfigKeepAlive(payload));
        }
    }

    public void onKeepAlive(final long payload) {
        if (payload != this.payload) {
            connection.disconnect(new RawComponent("Invalid payload"));
        }
    }
}