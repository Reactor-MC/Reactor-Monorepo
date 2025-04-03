package ink.reactor.server.network;

import ink.reactor.server.network.codec.PacketDecoder;
import ink.reactor.server.network.codec.PacketEncoder;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;

public final class SocketInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(final SocketChannel channel) {
        final ReactorConnection connection = new ReactorConnection(channel);

        final ChannelConfig config = channel.config();
        config.setOption(ChannelOption.TCP_NODELAY, true);
        config.setOption(ChannelOption.IP_TOS, 0x18);
        config.setAllocator(ByteBufAllocator.DEFAULT);

        channel.pipeline()
            .addLast("inbound", new NetworkInboundChannel(connection))
            .addLast("packet_encoder", new PacketEncoder())
            .addLast("packet_decoder", new PacketDecoder());
    }
}
