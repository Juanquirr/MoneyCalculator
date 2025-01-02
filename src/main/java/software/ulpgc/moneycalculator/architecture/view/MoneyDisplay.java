package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Money;

public interface MoneyDisplay {
    Money get();
    MoneyDisplay set(Money money);
}
