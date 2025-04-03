package ink.reactor.server.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public final class SocketServer {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ChannelFuture future;

    /**
     * Binds the Reactor server to the specified IP address and port.
     *
     * @param ip The IP address to which the Reactor server should bind.
     * @param port The port number to which the Reactor server should bind.
     */
    public void bind(final String ip, final int port) {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();

        Class<? extends ServerSocketChannel> socketChannel;
        if (Epoll.isAvailable()) {
            bossGroup = new EpollEventLoopGroup();
            workerGroup = new EpollEventLoopGroup(availableProcessors);
            socketChannel = EpollServerSocketChannel.class;
        } else if (KQueue.isAvailable()) {
            bossGroup = new KQueueEventLoopGroup();
            workerGroup = new KQueueEventLoopGroup(availableProcessors);
            socketChannel = KQueueServerSocketChannel.class;
        } else {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup(availableProcessors);
            socketChannel = NioServerSocketChannel.class;
        }

        future = new ServerBootstrap()
            .channel(socketChannel)
            .childHandler(new SocketInitializer())
            .group(bossGroup, workerGroup)
            .localAddress(ip, port)
            .bind()
            .syncUninterruptibly();
    }

    /**
     * Shuts down the Reactor server gracefully.
     * This method initiates a controlled shutdown sequence, closing all active connections
     * and releasing associated resources. The server cannot be reused after calling this method.
     * For restarting, a new server instance must be created.
     */
    public void shutdown() {
        try {
            future.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
