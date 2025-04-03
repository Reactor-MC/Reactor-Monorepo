package ink.reactor.server.network;

import ink.reactor.api.player.network.PlayerConnection;
import io.netty.channel.socket.SocketChannel;
import lombok.Getter;

@Getter
public final class ReactorConnection implements PlayerConnection {

    private final SocketChannel socket;

    public ReactorConnection(SocketChannel channel) {
        this.socket = channel;
    }
}
