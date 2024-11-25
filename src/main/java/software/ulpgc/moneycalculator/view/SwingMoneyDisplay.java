package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.model.MoneyDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {

    public SwingMoneyDisplay() {
        this.setText("PEPITO 123");
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setFont(new Font("Arial", Font.BOLD, 20));
    }

    @Override
    public void show(Money money) {
        this.setText(money.toString());
    }
}
