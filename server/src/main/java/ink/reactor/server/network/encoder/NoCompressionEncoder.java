package ink.reactor.server.network.encoder;

import org.tinylog.Logger;

import ink.reactor.api.player.connection.PacketOutbound;
import ink.reactor.buffer.DataSize;
import ink.reactor.buffer.writer.ExpectedSizeBuffer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public final class NoCompressionEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        final PacketOutbound packet = (PacketOutbound)msg;
        final byte[] packetBuffer = packet.write();
        final int outboundIdLength = DataSize.varInt(packet.getId());
        final int packetLength = packetBuffer.length + outboundIdLength;

        final ExpectedSizeBuffer header = new ExpectedSizeBuffer(DataSize.varInt(packetLength) + outboundIdLength);

        header.writeVarInt(packetLength);
        header.writeVarInt(packet.getId());

        ctx.write(Unpooled.wrappedBuffer(header.buffer));
        ctx.write(Unpooled.wrappedBuffer(packetBuffer));
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        Logger.error(cause, "Error encoding packet");
        ctx.close();
    }
}