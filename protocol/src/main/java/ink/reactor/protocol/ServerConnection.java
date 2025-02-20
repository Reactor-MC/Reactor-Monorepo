package ink.reactor.protocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ink.reactor.protocol.handler.play.PlayHandler;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.tinylog.Logger;

import ink.reactor.protocol.handler.configuration.ConfigurationHandler;
import ink.reactor.protocol.handler.handshake.HandshakeHandler;
import ink.reactor.protocol.handler.login.LoginHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public final class ServerConnection {
    private static final WriteBufferWaterMark SERVER_WRITE_MARK = new WriteBufferWaterMark(1 << 20, 1 << 21);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ChannelFuture future;

    private final List<PlayerConnectionImpl> playersNetwork = Collections.synchronizedList(new ArrayList<>());

    public void connect(final String ip, final int port) {
        final int workerThreadCount = 1; // Late change to Runtime.getRuntime().availableProcessors();

        Class<? extends ServerSocketChannel> socketChannel;
		if (Epoll.isAvailable()) {
            bossGroup = new EpollEventLoopGroup();
            workerGroup = new EpollEventLoopGroup(workerThreadCount);
            socketChannel = EpollServerSocketChannel.class;
        } else if (KQueue.isAvailable()) {
            bossGroup = new KQueueEventLoopGroup();
            workerGroup = new KQueueEventLoopGroup(workerThreadCount);
            socketChannel = KQueueServerSocketChannel.class;
        } else {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup(workerThreadCount);
            socketChannel = NioServerSocketChannel.class;
        }

        future = new ServerBootstrap().channel(socketChannel)
            .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, SERVER_WRITE_MARK)
            .childHandler(new PlayerChannelInitializer(playersNetwork))
            .group(bossGroup, workerGroup)
            .localAddress(ip, port)
            .bind()
            .syncUninterruptibly();
    }

    public void registerDefaultHandlers() {
        HandshakeHandler.registerHandlers();
        LoginHandler.registerHandlers();
        ConfigurationHandler.registerHandlers();
        PlayHandler.registerHandlers();
    }

    public void tick() {
        if (playersNetwork.isEmpty()) {
            return;
        }

        final Iterator<PlayerConnectionImpl> iterator = playersNetwork.iterator();

        while (iterator.hasNext()) {
            final PlayerConnectionImpl connection = iterator.next();

            if (!connection.getChannel().isActive()) {
                connection.getChannel().close();
                iterator.remove();
                continue;
            }

            connection.getKeepAliveManager().keepAlive();
        }
    }

    public void shutdown() {
        try {
            future.channel().close().sync();
        } catch(InterruptedException e) {
            Logger.error(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}