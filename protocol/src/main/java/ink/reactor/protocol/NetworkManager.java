package ink.reactor.protocol;

import ink.reactor.api.player.connection.ProtocolConnector;
import ink.reactor.protocol.decoder.PacketDecoded;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class NetworkManager extends SimpleChannelInboundHandler<PacketDecoded> {

    private final PlayerConnectionImpl connection;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PacketDecoded packetDecoded) throws Exception {
        connection.state.handlerStorage.execute(connection, packetDecoded);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (connection.getPlayer() != null) {
            ProtocolConnector.getPlayerCleanup().accept(connection.getPlayer());
        }
    }
}
