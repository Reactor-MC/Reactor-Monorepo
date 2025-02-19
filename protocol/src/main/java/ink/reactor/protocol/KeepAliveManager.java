package ink.reactor.protocol;

import org.tinylog.Logger;

import ink.reactor.chat.component.RawComponent;
import ink.reactor.protocol.outbound.configuration.PacketOutConfigKeepAlive;
import ink.reactor.protocol.outbound.play.PacketOutPlayKeepAlive;
import lombok.Getter;

@Getter
public final class KeepAliveManager {

    private final PlayerConnectionImpl connection;
    private long payload;
    private long lastKeepAlive = System.currentTimeMillis();

    private int countdown = 5;

    KeepAliveManager(PlayerConnectionImpl playerConnectionImpl) {
        this.connection = playerConnectionImpl;
    }

    public void keepAlive() {
        if (--countdown > 0) {
            return;
        }

        Logger.info("KEEP ALIVE SENd");
        countdown = 15;
        payload = System.currentTimeMillis();

        if (connection.getState() == ConnectionState.PLAY) {
            connection.sendPacket(new PacketOutPlayKeepAlive(payload));
            return;
        }
        if (connection.getState() == ConnectionState.CONFIGURATION) {
            connection.sendPacket(new PacketOutConfigKeepAlive(payload));
        }
    }

    public void tryKick(final long currentTime) {
        if (currentTime - lastKeepAlive >= 15000) {
            connection.disconnect(new RawComponent("Keep alive"));
        }
    }

    public void onKeepAlive(final long payload) {
        if (payload != this.payload) {
            connection.disconnect(new RawComponent("Invalid payload"));
            return;
        }
        this.lastKeepAlive = System.currentTimeMillis();
    }

    public void setLastKeepAlive(long lastKeepAlive) {
        this.lastKeepAlive = lastKeepAlive;
    }
}
