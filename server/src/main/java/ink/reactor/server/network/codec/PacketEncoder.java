package ink.reactor.server.network.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public final class PacketEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(final ChannelHandlerContext context, final Object message, final ChannelPromise promise) {

    }

    @Override
    public void handlerRemoved(final ChannelHandlerContext context) {
        context.close();
    }
}
