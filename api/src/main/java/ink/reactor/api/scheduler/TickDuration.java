package ink.reactor.api.scheduler;

import java.time.Duration;

public record TickDuration(int duration) {

    public static TickDuration ofMillis(final int millis) {
        return new TickDuration(TickUnit.MILLIS.toTicks(millis));
    }

    public static TickDuration none() {
        return new TickDuration(0);
    }

    public static TickDuration ofSeconds(final int seconds) {
        return new TickDuration(TickUnit.SECONDS.toTicks(seconds));
    }

    public static TickDuration ofMinutes(final int minutes) {
        return new TickDuration(TickUnit.MINUTES.toTicks(minutes));
    }

    public static TickDuration ofHours(final int hours) {
        return new TickDuration(TickUnit.HOURS.toTicks(hours));
    }

    public static TickDuration ofDays(final int days) {
        return new TickDuration(TickUnit.DAYS.toTicks(days));
    }

    public static TickDuration ofDuration(final Duration duration) {
        return new TickDuration(TickUnit.ticksFrom(duration));
    }
}