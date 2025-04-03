package ink.reactor.server.network.codec;

import ink.reactor.protocol.outbound.OutboundPacket;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public final class PacketEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(final ChannelHandlerContext context, final Object message, final ChannelPromise promise) {
        final OutboundPacket packet = (OutboundPacket) message;
        final byte[] packetBuffer = packet.write();
        final int outboundIdLength = DataSize.varInt(packet.getPacketId());
        final int packetLength = packetBuffer.length + outboundIdLength;

        final ExpectedSizeBuffer header = new ExpectedSizeBuffer(DataSize.varInt(packetLength) + outboundIdLength);

        header.writeVarInt(packetLength);
        header.writeVarInt(packet.getId());

        context.write(Unpooled.wrappedBuffer(header.buffer));
        context.write(Unpooled.wrappedBuffer(packetBuffer));
    }

    @Override
    public void handlerRemoved(final ChannelHandlerContext context) {
        context.close();
    }
}
