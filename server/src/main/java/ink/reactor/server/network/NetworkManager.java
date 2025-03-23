package ink.reactor.server.network;

import ink.reactor.chat.component.RawComponent;
import ink.reactor.protocol.ProtocolConnector;
import ink.reactor.protocol.inbound.PacketDecoded;
import ink.reactor.protocol.handler.PacketHandlerManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import lombok.RequiredArgsConstructor;
import org.tinylog.Logger;

@RequiredArgsConstructor
public final class NetworkManager extends SimpleChannelInboundHandler<PacketDecoded> {

    private final PlayerConnectionImpl connection;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PacketDecoded packetDecoded) {
        final PacketHandlerManager[] handlerManagers = connection.state.handlerManagers;
        final int id = packetDecoded.id();

        if (id >= 0 && id < handlerManagers.length) {
            final PacketHandlerManager handlerManager = handlerManagers[id];
            if (handlerManager != null) {
                handlerManager.execute(connection, packetDecoded);
            }
        } else {
            Logger.info("Player " + connection.getPlayer() + " send a invalid packet id " + id + " - state: " + connection.state);
            connection.disconnect(new RawComponent("Invalid packet id: " + id + " - state: " + connection.state));
        }

        packetDecoded.data().buffer.release();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (connection.getPlayer() != null) {
            ProtocolConnector.getPlayerCleanup().accept(connection.getPlayer());
        }
        connection.disconnect();
    }
}
