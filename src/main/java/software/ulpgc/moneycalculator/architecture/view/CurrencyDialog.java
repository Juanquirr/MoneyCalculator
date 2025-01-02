package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.Map;

public interface CurrencyDialog {
    CurrencyDialog define(Map<String, Currency> currencies);
    Currency get();
    CurrencyDialog set(Currency currency);
}
