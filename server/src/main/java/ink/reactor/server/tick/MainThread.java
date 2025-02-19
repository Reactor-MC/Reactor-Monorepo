package ink.reactor.server.tick;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.tinylog.Logger;

import ink.reactor.api.scheduler.ServerScheduler;
import ink.reactor.protocol.ServerConnection;

public final class MainThread implements Runnable {

    private final TickScheduler scheduler = new TickScheduler();
    private final ScheduledExecutorService scheduledExecutorService;
    private final ServerConnection serverConnection;

    public MainThread(ServerConnection serverConnection, ScheduledExecutorService scheduledExecutorService) {
        this.serverConnection = serverConnection;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void start() {
        scheduledExecutorService.scheduleAtFixedRate(this, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void stop(final Runnable onStop) {       
        try {
            serverConnection.shutdown();

            scheduledExecutorService.shutdown();
            scheduledExecutorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (final Exception e) {
            Logger.error(e, "Error stopping the main thread");
        } finally {
            onStop.run();
        }
    }

    @Override
    public void run() {
        System.out.println("ASD");
        Logger.info("TICKING MIAN");
        serverConnection.tick();
        scheduler.tick();
    }

    public ServerScheduler getScheduler() {
        return scheduler;
    }
}