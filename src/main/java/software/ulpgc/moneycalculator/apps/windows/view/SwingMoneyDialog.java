package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.customization.CustomizedComponent;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.view.CurrencyDialog;
import software.ulpgc.moneycalculator.view.MoneyDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Map;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private final SwingCurrencyDialog currencyDialog;
    private JPanel currencyToolbar;

    public SwingMoneyDialog() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(90, 121, 200));
        this.setBorder(new LineBorder(Color.black, 4, true));
        add(this.currencyDialog = createCurrencyDialog());
        add(Box.createVerticalStrut(75));
        add(moneyDialogTextField());
        add(Box.createVerticalStrut(75));
        add(createCurrencyToolbar());
    }

    @Override
    public MoneyDialog define(Map<String, Currency> currencies) {
        this.currencyDialog.define(currencies);
        return this;
    }

    private JPanel moneyDialogTextField() {
        JPanel panel = new CustomizedComponent().customizePanel(new JPanel());
        panel.add(this.amountField = new CustomizedComponent().customizeTextField(new SwingAmountEntry()));
        return panel;
    }

    private SwingCurrencyDialog createCurrencyDialog() {
        return new SwingCurrencyDialog();
    }

    private JPanel createCurrencyToolbar() {
        this.currencyToolbar = new JPanel();
        this.currencyToolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.currencyToolbar.setOpaque(false);
        return this.currencyToolbar;
    }

    public SwingMoneyDialog addButtonsToToolbar(JButton... buttons) {
        for (JButton button : buttons) {
            this.currencyToolbar.add(button);
        }
        revalidate();
        repaint();
        return this;
    }

    @Override
    public Money get() {
        return new Money(toDouble(amountField.getText()), currencyDialog.get());
    }

    @Override
    public MoneyDialog set(Money money) {
        amountField.setText(String.valueOf(money.amount()));
        return this;
    }

    private double toDouble(String text) {
        return text.equals("Enter amount") ? 0 : Double.parseDouble(text);
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }
}



