package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Money;

public interface MoneyDisplay {
    void show(Money money);
    Money get();

    MoneyDisplay set(Money money);
}
