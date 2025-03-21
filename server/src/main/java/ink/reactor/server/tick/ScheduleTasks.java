package ink.reactor.server.tick;

import lombok.AllArgsConstructor;

final class ScheduleTasks {

    private int taskIdCount = 0;
    private ScheduleTask[] tasks = new ScheduleTask[16];
    private int runningTasks = 0;
    private int pos = 0;

    void executeAll() {
        if (runningTasks == 0) {
            return;
        }
        for (final ScheduleTask task : tasks) {
            if (task == null) {
                break;
            }
            if (--task.countdown <= 0) {
                task.countdown = task.delay;
                task.runnable.run();
            }
        }
    }

    boolean removeTask(final int id) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                break;
            }
            if (tasks[i].id == id) {
                tasks[i] = null;
                runningTasks--;
                compactTaskArray();
                return true;
            }
        }
        return false;
    }

    int addTask(final Runnable runnable, final int startDelay, final int delay) {
        if (runningTasks >= tasks.length) {
            final ScheduleTask[] newTasks = new ScheduleTask[tasks.length * 2];
            System.arraycopy(tasks, 0, newTasks, 0, newTasks.length);
            tasks = newTasks;
        }
        tasks[pos++] = new ScheduleTask(runnable, ++taskIdCount, delay, startDelay);
        runningTasks++;
        return taskIdCount;
    }

    private void compactTaskArray() {
        final ScheduleTask[] newTasks = new ScheduleTask[tasks.length];
        int index = 0;
        for (final ScheduleTask task : tasks) {
            if (task != null) {
                newTasks[index++] = task;
            }
        }
        this.tasks = newTasks;
        this.pos = index;
    }

    @AllArgsConstructor
    private static final class ScheduleTask {
        private final Runnable runnable;
        private final int id;
        private final int delay;
        private int countdown;
    }
}