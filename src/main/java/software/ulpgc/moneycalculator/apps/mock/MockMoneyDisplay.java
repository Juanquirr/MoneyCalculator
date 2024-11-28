package software.ulpgc.moneycalculator.apps.mock;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.view.MoneyDisplay;

public class MockMoneyDisplay implements MoneyDisplay {
    @Override
    public void show(Money money) {
        System.out.println(money);
    }
}
