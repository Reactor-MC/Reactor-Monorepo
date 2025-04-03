package ink.reactor.server.network;

import ink.reactor.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class NetworkInboundChannel extends SimpleChannelInboundHandler<Packet> {

    private final ReactorConnection connection;

    @Override
    protected void channelRead0(final ChannelHandlerContext context, final Packet packet) {

    }

    @Override
    public void handlerRemoved(final ChannelHandlerContext context) {

    }
}
