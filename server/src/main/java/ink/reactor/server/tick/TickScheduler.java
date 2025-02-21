package ink.reactor.server.tick;

import ink.reactor.api.scheduler.ServerScheduler;
import ink.reactor.api.scheduler.TickDuration;

final class TickScheduler implements ServerScheduler {

    private final NowTasks nowTasks = new NowTasks();
    private final LaterTasks laterTasks = new LaterTasks();
    private final ScheduleTasks scheduleTasks = new ScheduleTasks();

    void tick() {
        nowTasks.executeAll();
        laterTasks.executeAll();
        scheduleTasks.executeAll();
    }

    @Override
    public void runNow(final Runnable task) {
        nowTasks.addTask(task);
    }

    @Override
    public void runLater(final Runnable task, final TickDuration delay) {
        final int ticks = delay.duration();
        if (ticks <= 0) {
            nowTasks.addTask(task);
            return;
        }
        laterTasks.addTask(task, delay.duration());
    }

    @Override
    public int schedule(final Runnable task, final TickDuration startDelay, final TickDuration repeat) {
        return scheduleTasks.addTask(task, startDelay.duration(), repeat.duration());
    }

    @Override
    public boolean cancelTask(final int taskId) {
        return scheduleTasks.removeTask(taskId);
    }
}