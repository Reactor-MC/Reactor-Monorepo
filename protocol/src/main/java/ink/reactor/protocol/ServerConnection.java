package ink.reactor.protocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
		if (Epoll.isAvailable()) {
            bossGroup = new EpollEventLoopGroup();
            workerGroup = new EpollEventLoopGroup(workerThreadCount);
        } else if (KQueue.isAvailable()) {
            bossGroup = new KQueueEventLoopGroup();
            workerGroup = new KQueueEventLoopGroup(workerThreadCount);
        } else {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup(workerThreadCount);
        }

        future = new ServerBootstrap().channel(EpollServerSocketChannel.class)
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
    }

    public void tick() {
        if (playersNetwork.isEmpty()) {
            return;
        }

        final Iterator<PlayerConnectionImpl> iterator = playersNetwork.iterator();
        while (iterator.hasNext()) {
            final PlayerConnectionImpl connection = iterator.next();

            //connection.keepAlive();

            if (!connection.getChannel().isActive()) {
                connection.getChannel().close();
                iterator.remove();
                continue;
            }
        }
    }

    public void shutdown() {
        try {
            future.channel().close().sync();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}