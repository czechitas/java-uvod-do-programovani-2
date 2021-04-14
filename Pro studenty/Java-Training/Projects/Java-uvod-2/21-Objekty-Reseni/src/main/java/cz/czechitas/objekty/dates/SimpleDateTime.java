package cz.czechitas.objekty.dates;

public class SimpleDateTime {

    SimpleDate date;
    SimpleTime time;

    public SimpleDateTime() {
        date = new SimpleDate();
        time = new SimpleTime();
    }

    public SimpleDateTime(SimpleDate date, SimpleTime time) {
        this.date = date;
        this.time = time;
    }

    public SimpleDate getDate() {
        return date;
    }

    public SimpleTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SimpleDateTime)) {
            return false;
        }
        return time.equals(((SimpleDateTime) other).time) && date.equals(((SimpleDateTime) other).date);
    }

    @Override
    public int hashCode() {
        return time.hashCode() + 31 * date.hashCode();
    }

    @Override
    public String toString() {
        return date.toString() + "T" + time.toString();
    }

}
