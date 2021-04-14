package cz.czechitas.objekty.dates;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

public enum WeekDay {

    MONDAY (DayOfWeek.MONDAY),
    TUESDAY (DayOfWeek.TUESDAY),
    WEDNESDAY (DayOfWeek.WEDNESDAY),
    THURSDAY (DayOfWeek.THURSDAY),
    FRIDAY (DayOfWeek.FRIDAY),
    SATURDAY (DayOfWeek.SATURDAY),
    SUNDAY (DayOfWeek.SUNDAY);

    private DayOfWeek actualDay;

    WeekDay(DayOfWeek actualDayOfWeek) {
        this.actualDay = actualDayOfWeek;
    }

    public static WeekDay of(DayOfWeek dayOfWeek) {
        for (WeekDay value : values()) {
            if (value.actualDay == dayOfWeek) return value;
        }
        throw new IllegalArgumentException("Invalid " + dayOfWeek);
    }

    @Override
    public String toString() {
        return DateTimeFormatter.ofPattern("EEEE").format(actualDay);
    }
}
