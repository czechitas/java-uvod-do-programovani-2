package cz.czechitas.objekty.dates;

import java.time.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class SimpleDate {

    private java.time.LocalDate actualDate;

    SimpleDate(LocalDate actualDate) {
        this.actualDate = actualDate;
    }

    public SimpleDate() {
        actualDate = java.time.LocalDate.now();
    }

    public SimpleDate(int year, int month, int dayOfMonth) {
        actualDate = java.time.LocalDate.of(year, month, dayOfMonth);
    }

    public SimpleDate(String text) {
        actualDate = java.time.LocalDate.parse(text);
    }

    public int getYear() {
        return actualDate.getYear();
    }

    public int getMonth() {
        return actualDate.getMonthValue();
    }

    public int getDay() {
        return actualDate.getDayOfMonth();
    }

    public int getDayOfYear() {
        return actualDate.getDayOfYear();
    }

    public WeekDay getDayOfWeek() {
        return WeekDay.of(actualDate.getDayOfWeek());
    }

    public boolean isLeapYear() {
        return actualDate.isLeapYear();
    }

    public int lengthOfMonth() {
        return actualDate.lengthOfMonth();
    }

    public int lengthOfYear() {
        return actualDate.lengthOfYear();
    }

    public SimpleDate withYear(int year) {
        return new SimpleDate(actualDate.withYear(year));
    }

    public SimpleDate withMonth(int month) {
        return new SimpleDate(actualDate.withMonth(month));
    }

    public SimpleDate withDayOfMonth(int dayOfMonth) {
        return new SimpleDate(actualDate.withDayOfMonth(dayOfMonth));
    }

    public SimpleDate withDayOfYear(int dayOfYear) {
        return new SimpleDate(actualDate.withDayOfYear(dayOfYear));
    }

    public SimpleDate plusYears(long yearsToAdd) {
        return new SimpleDate(actualDate.plusYears(yearsToAdd));
    }

    public SimpleDate plusMonths(long monthsToAdd) {
        return new SimpleDate(actualDate.plusMonths(monthsToAdd));
    }

    public SimpleDate plusWeeks(long weeksToAdd) {
        return new SimpleDate(actualDate.plusWeeks(weeksToAdd));
    }

    public SimpleDate plusDays(long daysToAdd) {
        return new SimpleDate(actualDate.plusDays(daysToAdd));
    }

    public SimpleDate minusYears(long yearsToSubtract) {
        return new SimpleDate(actualDate.minusYears(yearsToSubtract));
    }

    public SimpleDate minusMonths(long monthsToSubtract) {
        return new SimpleDate(actualDate.minusMonths(monthsToSubtract));
    }

    public SimpleDate minusWeeks(long weeksToSubtract) {
        return new SimpleDate(actualDate.minusWeeks(weeksToSubtract));
    }

    public SimpleDate minusDays(long daysToSubtract) {
        return new SimpleDate(actualDate.minusDays(daysToSubtract));
    }

    public DateDuration between(SimpleDate other) {
        return new DateDuration(Period.between(other.actualDate, actualDate));
    }

    public long betweenTotalDays(SimpleDate other) {
        return DAYS.between(other.actualDate, actualDate);
    }

    public LocalDateTime atStartOfDay() {
        return actualDate.atStartOfDay();
    }

    public boolean isAfter(SimpleDate other) {
        return actualDate.isAfter(other.actualDate);
    }

    public boolean isBefore(SimpleDate other) {
        return actualDate.isBefore(other.actualDate);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SimpleDate)) {
            return false;
        }
        return actualDate.equals(((SimpleDate) other).actualDate);
    }

    @Override
    public int hashCode() {
        return actualDate.hashCode();
    }

    @Override
    public String toString() {
        return actualDate.toString();
    }
}
