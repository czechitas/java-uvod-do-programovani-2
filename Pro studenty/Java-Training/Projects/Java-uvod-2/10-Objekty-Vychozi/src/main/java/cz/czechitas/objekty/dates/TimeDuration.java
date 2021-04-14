package cz.czechitas.objekty.dates;

import java.time.Duration;

public class TimeDuration {

    private Duration actualDuration;

    public TimeDuration(Duration duration) {
        this.actualDuration = duration;
    }

    public TimeDuration(long days, int hours, int minutes, int seconds) {
        actualDuration = Duration.ofDays(days).plus(Duration.ofHours(hours)).plus(Duration.ofMinutes(minutes)).plus(Duration.ofSeconds(seconds));
    }

    public TimeDuration(int hours, int minutes, int seconds) {
        actualDuration = Duration.ofHours(hours).plus(Duration.ofMinutes(minutes)).plus(Duration.ofSeconds(seconds));
    }

    public TimeDuration(long totalSeconds) {
        actualDuration = Duration.ofSeconds(totalSeconds);
    }

    public long getDays() {
        return actualDuration.toDaysPart();
    }

    public int getHours() {
        return actualDuration.toHoursPart();
    }

    public int getMinutes() {
        return actualDuration.toMinutesPart();
    }

    public int getSeconds() {
        return actualDuration.toSecondsPart();
    }

    public long getTotalSeconds() {
        return actualDuration.toSeconds();
    }

    public boolean isZero() {
        return actualDuration.isZero();
    }

    public boolean isNegative() {
        return actualDuration.isNegative();
    }

    public TimeDuration negated() {
        return new TimeDuration(actualDuration.negated());
    }

    public TimeDuration abs() {
        return new TimeDuration(actualDuration.abs());
    }

    public int compareTo(TimeDuration otherDuration) {
        return actualDuration.compareTo(otherDuration.actualDuration);
    }

    @Override
    public boolean equals(Object otherDuration) {
        if (!(otherDuration instanceof TimeDuration)) {
            return false;
        }
        return actualDuration.equals(((TimeDuration) otherDuration).actualDuration);
    }

    @Override
    public int hashCode() {
        return actualDuration.hashCode();
    }

    @Override
    public String toString() {
        return actualDuration.toString();
    }
}
