package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {

    public SwingMoneyDisplay() {

    }

    @Override
    public void show(Money money) {
        this.setText(money.toString());
    }
}
