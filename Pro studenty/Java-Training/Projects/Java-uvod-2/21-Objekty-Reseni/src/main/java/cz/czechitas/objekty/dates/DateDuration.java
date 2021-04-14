package cz.czechitas.objekty.dates;

import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateDuration {

    private Period actualPeriod;

    DateDuration(Period actualPeriod) {
        this.actualPeriod = actualPeriod;
    }

    public DateDuration(int year, int month, int dayOfMonth) {
        actualPeriod = Period.of(year, month, dayOfMonth);
    }

    public int getYears() {
        return actualPeriod.getYears();
    }

    public int getMonths() {
        return actualPeriod.getMonths();
    }

    public int getDays() {
        return actualPeriod.getDays();
    }

    public boolean isZero() {
        return actualPeriod.isZero();
    }

    public boolean isNegative() {
        return actualPeriod.isNegative();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateDuration)) {
            return false;
        }
        return actualPeriod.equals(((DateDuration) obj).actualPeriod);
    }

    @Override
    public int hashCode() {
        return actualPeriod.hashCode();
    }

    @Override
    public String toString() {
        return actualPeriod.toString();
    }
}
