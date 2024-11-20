package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.model.MoneyDisplay;

public class MockMoneyDisplay implements MoneyDisplay {
    @Override
    public void show(Money money) {
        System.out.println(money);
    }
}
