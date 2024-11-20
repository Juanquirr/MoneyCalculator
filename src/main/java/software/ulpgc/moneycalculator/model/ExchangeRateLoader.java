package software.ulpgc.moneycalculator.model;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
