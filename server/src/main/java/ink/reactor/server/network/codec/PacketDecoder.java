package ink.reactor.server.network.codec;

import ink.reactor.protocol.Packet;
import ink.reactor.protocol.PacketData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public final class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(final ChannelHandlerContext context, final ByteBuf buffer, final List<Object> out) {
        if (buffer.readableBytes() < 1) {
            return;
        }

        buffer.markReaderIndex();
        final int packetLength = PacketData.readVarIntSafely(buffer);

        if (packetLength == -1) {
            buffer.resetReaderIndex();
            return;
        }

        if (packetLength <= 0) {
            throw new IllegalArgumentException("Invalid packet length: " + packetLength);
        }

        if (buffer.readableBytes() < packetLength) {
            buffer.resetReaderIndex();
            return;
        }

        final ByteBuf packetData = buffer.readBytes(packetLength);
        final PacketData packetInData = new PacketData(packetData);
        final int id = packetInData.readVarInt();

        out.add(new Packet(id, packetInData));
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
        context.close();
    }
}
