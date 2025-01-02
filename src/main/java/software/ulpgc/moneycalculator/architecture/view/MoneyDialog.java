package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.Money;

import java.util.Map;

public interface MoneyDialog {
    MoneyDialog define(Map<String, Currency> currencies);
    Money get();
    MoneyDialog set(Money money);
}
