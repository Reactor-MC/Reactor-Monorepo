package ink.reactor.api.scheduler;

import java.time.Duration;

public final class TickUnit {

    public static final long MILLIS_PER_TICK = 50;

    public static final TickUnit
        MILLIS = new TickUnit(1),
        SECONDS = new TickUnit(1000),
        MINUTES = new TickUnit(SECONDS.millis * 60),
        HOURS = new TickUnit(MINUTES.millis * 60),
        DAYS = new TickUnit(HOURS.millis * 24);

    private final long millis;

    private TickUnit(long millis) {
        this.millis = millis;
    }

    public int toTicks(final int unit) {
        return Math.round((float) (unit * millis) / MILLIS_PER_TICK);
    }

    public long toUnit(final int ticks) {
        return (MILLIS_PER_TICK * ticks) / millis;
    }

    public long toMillis(final int unit) {
        return millis * unit;
    }

    public double toSeconds(final int unit) {
        return (double) (millis * unit) / SECONDS.millis;
    }

    public double toMinutes(final int unit) {
        return (double) (millis * unit) / MINUTES.millis;
    }

    public double toHours(final int unit) {
        return (double) (millis * unit) / HOURS.millis;
    }

    public double toDays(final int unit) {
        return (double) (millis * unit) / DAYS.millis;
    }

    public static int ticksFrom(final Duration duration) {
        return (int) (duration.toMillis() / MILLIS_PER_TICK);
    }
}