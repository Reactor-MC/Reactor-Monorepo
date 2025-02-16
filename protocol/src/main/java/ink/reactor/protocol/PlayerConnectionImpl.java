package ink.reactor.protocol;

import ink.reactor.api.player.Player;
import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.chat.component.ChatComponent;
import io.netty.channel.socket.SocketChannel;

public final class PlayerConnectionImpl implements PlayerConnection {

    private final SocketChannel channel;
    private Player player;

    public boolean isFirstConfig = true;

    public volatile ConnectionState state = ConnectionState.HANDSHAKE;

    PlayerConnectionImpl(SocketChannel channel) {
        this.channel = channel;
    }

    public Player getPlayer() {
        return player;
    };

    public SocketChannel getChannel() {
        return channel;
    }

    public void keepAlive() {
        if (state == ConnectionState.PLAY) {
            sendPacket(null);
            return;
        }
        if (state == ConnectionState.CONFIGURATION) {
            sendPacket(null);
        }
    }

    public void sendPacket(final PacketOutbound packet) {
        if (channel.eventLoop().inEventLoop()) {
            channel.writeAndFlush(packet);
            return;
        }
        channel.eventLoop().execute(() -> channel.writeAndFlush(packet));
    };

    public void sendPackets(final PacketOutbound... packets) {
        if (channel.eventLoop().inEventLoop()) {
            for (final PacketOutbound packet : packets) {
                channel.write(packet);
            }
            channel.flush();
            return;
        }

        channel.eventLoop().execute(() -> {
            for (final PacketOutbound packet : packets) {
                channel.write(packet);
            }
            channel.flush();
        });
    };

    public void disconnect(ChatComponent[] reason) {
        channel.close();
    };

    public void setPlayer(Player player) {
        this.player = player;
    }
}
