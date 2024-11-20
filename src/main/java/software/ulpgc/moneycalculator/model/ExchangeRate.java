package software.ulpgc.moneycalculator.model;

import java.time.LocalDate;
import java.util.Objects;

public class ExchangeRate {
    private final Currency from;
    private final Currency to;
    private final LocalDate date;
    private final double rate;

    public ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.rate = rate;
    }

    public Currency from() {
        return from;
    }

    public Currency to() {
        return to;
    }

    public LocalDate date() {
        return date;
    }

    public double rate() {
        return rate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ExchangeRate) obj;
        return Objects.equals(this.from, that.from) &&
                Objects.equals(this.to, that.to) &&
                Objects.equals(this.date, that.date) &&
                Double.doubleToLongBits(this.rate) == Double.doubleToLongBits(that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, date, rate);
    }

    @Override
    public String toString() {
        return "software.ulpgc.moneycalculator.model.ExchangeRate[" +
                "from=" + from + ", " +
                "to=" + to + ", " +
                "date=" + date + ", " +
                "rate=" + rate + ']';
    }

}
