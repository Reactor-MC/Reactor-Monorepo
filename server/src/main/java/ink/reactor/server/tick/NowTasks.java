package ink.reactor.server.tick;

import java.util.Arrays;

final class NowTasks {
    private Runnable[] tasks = new Runnable[16];
    private int pos = 0;

    public void addTask(Runnable task) {
        if (++pos == tasks.length) {
            tasks = Arrays.copyOf(tasks, tasks.length * 2);
        }
        tasks[pos] = task;
    }

    public void executeAll() {
        if (pos == 0) {
            return;
        }

        if (pos == 1) { // Common case
            pos = 0;
            tasks[0].run();
            tasks[0] = null;
            return;
        }

        final Runnable[] nowTasks = tasks;
        tasks = new Runnable[16];
        pos = 0;

        for (final Runnable task : nowTasks) {
            task.run();
        }
    }
}