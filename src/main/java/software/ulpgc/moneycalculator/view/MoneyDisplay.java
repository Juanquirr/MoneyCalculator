package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Money;

public interface MoneyDisplay {
    Money get();
    MoneyDisplay set(Money money);
}
