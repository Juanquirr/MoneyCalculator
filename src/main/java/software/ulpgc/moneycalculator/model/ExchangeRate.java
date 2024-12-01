package software.ulpgc.moneycalculator.model;

import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {

    @Override
    public String toString() {
        return "ExchangeRate[" +
                "from=" + from + ", " +
                "to=" + to + ", " +
                "date=" + date + ", " +
                "rate=" + rate + ']';
    }

}
