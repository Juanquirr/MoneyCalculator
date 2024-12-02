package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;

import java.util.Map;

public interface MoneyDialog {
    MoneyDialog define(Map<String, Currency> currencies);
    Money get();
    MoneyDialog set(Money money);
    void remove();
}
