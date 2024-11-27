package software.ulpgc.moneycalculator.model;

import java.time.LocalDate;
import java.util.Objects;

public record ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {

    @Override
    public String toString() {
        return "software.ulpgc.moneycalculator.model.ExchangeRate[" +
                "from=" + from + ", " +
                "to=" + to + ", " +
                "date=" + date + ", " +
                "rate=" + rate + ']';
    }

}
