package ink.reactor.api.player.connection;

import ink.reactor.api.player.Player;
import ink.reactor.chat.component.ChatComponent;

public interface PlayerConnection {
    void disconnect(final ChatComponent[] reason);
    void sendPacket(final PacketOutbound packet);
    void sendPackets(final PacketOutbound... packets);

    void ping();

    Player getPlayer();

    boolean isOnline();
}