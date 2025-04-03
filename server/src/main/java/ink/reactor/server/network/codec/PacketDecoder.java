package ink.reactor.server.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public final class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(final ChannelHandlerContext context, final ByteBuf buffer, final List<Object> out) {

    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
        context.close();
    }
}
