package cz.czechitas.objekty.dates;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;

import static java.time.temporal.ChronoUnit.SECONDS;

public class SimpleTime {

    private LocalTime actualTime;

    SimpleTime(LocalTime actualTime) {
        this.actualTime = actualTime;
    }

    public SimpleTime() {
        actualTime = LocalTime.now();
    }

    public SimpleTime(int hour, int minute) {
        actualTime =  LocalTime.of(hour, minute);
    }

    public SimpleTime(int hour, int minute, int second) {
        actualTime = LocalTime.of(hour, minute, second);
    }

    public int getHour() {
        return actualTime.getHour();
    }

    public int getMinute() {
        return actualTime.getMinute();
    }

    public int getSecond() {
        return actualTime.getSecond();
    }

    public SimpleTime plusHours(long hoursToAdd) {
        return new SimpleTime(actualTime.plusHours(hoursToAdd));
    }

    public SimpleTime plusMinutes(long minutesToAdd) {
        return new SimpleTime(actualTime.plusMinutes(minutesToAdd));
    }

    public SimpleTime plusSeconds(long secondstoAdd) {
        return new SimpleTime(actualTime.plusSeconds(secondstoAdd));
    }

    public SimpleTime minusHours(long hoursToSubtract) {
        return new SimpleTime(actualTime.minusHours(hoursToSubtract));
    }

    public SimpleTime minusMinutes(long minutesToSubtract) {
        return new SimpleTime(actualTime.minusMinutes(minutesToSubtract));
    }

    public SimpleTime minusSeconds(long secondsToSubtract) {
        return new SimpleTime(actualTime.minusSeconds(secondsToSubtract));
    }

    public int getSecondsOfDay() {
        return actualTime.toSecondOfDay();
    }

    public int compareTo(SimpleTime other) {
        return actualTime.compareTo(other.actualTime);
    }

    public boolean isAfter(SimpleTime other) {
        return actualTime.isAfter(other.actualTime);
    }

    public boolean isBefore(SimpleTime other) {
        return actualTime.isBefore(other.actualTime);
    }

    public TimeDuration between(SimpleTime other) {
        return new TimeDuration(Duration.between(other.actualTime, actualTime));
    }

    public long betweenTotalSeconds(SimpleTime other) {
        return SECONDS.between(other.actualTime, actualTime);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SimpleTime)) {
            return false;
        }
        return actualTime.equals(((SimpleTime) other).actualTime);
    }

    @Override
    public int hashCode() {
        return actualTime.hashCode();
    }

    @Override
    public String toString() {
        return actualTime.toString();
    }
}
