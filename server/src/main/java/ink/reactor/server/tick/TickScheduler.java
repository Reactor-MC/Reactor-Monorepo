package ink.reactor.server.tick;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import ink.reactor.api.scheduler.ServerScheduler;
import lombok.RequiredArgsConstructor;

final class TickScheduler implements ServerScheduler {

    private int tasksCount = 0;

    private final Collection<Runnable> nowTasks = new LinkedList<>();
    private final Collection<LaterTask> laterTasks = new LinkedList<>();
    private final Collection<ScheduleTask> scheduleTasks = new ArrayList<>();

    void tick() {
        if (!nowTasks.isEmpty()) {
            for (final Runnable task : nowTasks) {
                task.run();
            }
            nowTasks.clear();
        }

        if (!laterTasks.isEmpty()) {
            final Iterator<LaterTask> iterator = laterTasks.iterator();
            while (iterator.hasNext()) {
                final LaterTask task = iterator.next();
                if (--task.delay == 0) {
                    task.task.run();
                    iterator.remove();
                }
            }
        }

        if (!scheduleTasks.isEmpty()) {
            for (final ScheduleTask task : scheduleTasks) {
                if (task.startDelay > 0) {
                    task.startDelay--;
                    continue;
                }
                if (++task.timer == task.repeat) {
                    task.timer = 0;
                    task.task.run();
                }
            }
        }
    }

    @Override
    public void runNow(Runnable task) {
        nowTasks.add(task);
    }

    @Override
    public void runLater(Runnable task, int delay) {
        if (delay <= 0) {
            nowTasks.add(task);
            return;
        }
        final LaterTask laterTask = new LaterTask(task);
        laterTask.delay = delay;
        laterTasks.add(laterTask);
    }

    @Override
    public int schedule(Runnable task, int startDelay, int repeat) {
        final ScheduleTask scheduleTask = new ScheduleTask(tasksCount++, task, repeat);
        scheduleTask.startDelay = startDelay;
        scheduleTasks.add(scheduleTask);
        return scheduleTask.id;
    }

    @Override
    public boolean cancelTask(final int taskId) {
        if (scheduleTasks.isEmpty()) {
            return false;
        }
        final Iterator<ScheduleTask> iterator = scheduleTasks.iterator();
        while(iterator.hasNext()) {
            final ScheduleTask task = iterator.next();
            if (task.id == taskId) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @RequiredArgsConstructor
    static final class ScheduleTask{
        final int id;
        final Runnable task;
        final int repeat;
        int timer;
        int startDelay;
    }

    @RequiredArgsConstructor
    static final class LaterTask{
        final Runnable task;
        int delay;
    }
}