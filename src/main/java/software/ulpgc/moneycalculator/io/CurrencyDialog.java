package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;

import java.util.Map;

public interface CurrencyDialog {
    CurrencyDialog define(Map<String, Currency> currencies);
    Currency get();
    CurrencyDialog set(Currency currency);
}
