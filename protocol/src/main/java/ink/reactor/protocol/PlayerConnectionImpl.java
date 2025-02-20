package ink.reactor.protocol;

import ink.reactor.api.player.Player;
import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.api.player.connection.PlayerConnection;
import ink.reactor.chat.component.ChatComponent;

import ink.reactor.protocol.outbound.configuration.PacketOutConfigDisconnected;
import ink.reactor.protocol.outbound.login.PacketOutLoginDisconnect;
import ink.reactor.protocol.outbound.play.PacketOutPing;
import ink.reactor.protocol.outbound.play.PacketOutPlayDisconnected;
import io.netty.channel.socket.SocketChannel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PlayerConnectionImpl implements PlayerConnection {

    private final SocketChannel channel;
    private Player player;

    private boolean isFirstConfig = true;

    private final KeepAliveManager keepAliveManager = new KeepAliveManager(this);
    private long lastPing;

    public volatile ConnectionState state = ConnectionState.HANDSHAKE;

    PlayerConnectionImpl(SocketChannel channel) {
        this.channel = channel;
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

    public void ping() {
        if (state == ConnectionState.PLAY) {
            lastPing = System.currentTimeMillis();
            sendPacket(PacketOutPing.INSTANCE);
        }
    }

    public void disconnect(ChatComponent... reason) {
        if (reason == null) {
            channel.close();
            return;
        }
        if (state == ConnectionState.LOGIN) {
            sendPacket(new PacketOutLoginDisconnect(reason));
            return;
        }
        if (state == ConnectionState.PLAY) {
            sendPacket(new PacketOutPlayDisconnected(reason));
            return;
        }
        if (state == ConnectionState.CONFIGURATION) {
            sendPacket(new PacketOutConfigDisconnected(reason));
        }
    };
}
