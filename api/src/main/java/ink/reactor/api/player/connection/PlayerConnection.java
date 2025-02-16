package ink.reactor.api.player.connection;

import ink.reactor.api.player.Player;
import ink.reactor.chat.component.ChatComponent;

public interface PlayerConnection {
    public void disconnect(final ChatComponent[] reason);
    public void sendPacket(final PacketOutbound packet);
    public void sendPackets(final PacketOutbound... packets);

    public Player getPlayer();
}