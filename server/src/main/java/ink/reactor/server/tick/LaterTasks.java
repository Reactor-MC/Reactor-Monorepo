package ink.reactor.server.tick;

import lombok.AllArgsConstructor;

final class LaterTasks {

    private static final int MAX_EMPTY_ARRAY_SIZE = 256;
    private static final int DEFAULT_ARRAY_SIZE = 16;

    private LaterTask[] tasks;
    private int waitingTasks = 0;
    private int pos = 0;

    public LaterTasks() {
        this.tasks = new LaterTask[DEFAULT_ARRAY_SIZE];
    }

    public void addTask(final Runnable task, final int delay) {
        if (waitingTasks == tasks.length) {
            compactAndResize();
        }
        tasks[pos++] = new LaterTask(task, delay);
        waitingTasks++;
    }

    public void executeAll() {
        if (waitingTasks == 0) {
            if (tasks.length == MAX_EMPTY_ARRAY_SIZE) { // Auto cleanup
                tasks = new LaterTask[DEFAULT_ARRAY_SIZE];
                pos = 0;
            }
            return;
        }

        boolean needCompact = false;

        for (int i = 0; i < pos; i++) {
            final LaterTask task = tasks[i];
            if (task == null) {
                break;
            }
            if (--task.delay <= 0) {
                task.task.run();
                tasks[i] = null;
                waitingTasks--;
                needCompact = true;
            }
        }

        if (needCompact) {
            compact();
        }
    }

    private void compactAndResize() {
        compact();
        final LaterTask[] newLaterTasks = new LaterTask[nextPowerOfTwo(pos+1)];
        System.arraycopy(tasks, 0, newLaterTasks, 0, pos);
        this.tasks = newLaterTasks;
    }

    private void compact() {
        int index = 0;
        for (int i = 0; i < pos; i++) {
            if (tasks[i] != null) {
                tasks[index++] = tasks[i];
            }
        }
        pos = index;
    }

    @AllArgsConstructor
    private static final class LaterTask {
        final Runnable task;
        int delay;
    }

    private static int nextPowerOfTwo(int value) {
        if (value <= 0) {
            return 1;
        }
        value--;
        value |= value >> 1;
        value |= value >> 2;
        value |= value >> 4;
        value |= value >> 8;
        value |= value >> 16;
        value++;
        return value;
    }
}
