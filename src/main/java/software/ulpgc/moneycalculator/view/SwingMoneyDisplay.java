package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.io.CurrencyDialog;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Locale;
import java.util.Map;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {

    private JTextField amountField;
    private final SwingCurrencyDialog currencyDialog;
    private JPanel currencyToolbar;

    public SwingMoneyDisplay() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(90, 121, 200));
        this.setBorder(new LineBorder(Color.black, 4, true));
        add(this.currencyDialog = createCurrencyDialog());
        add(Box.createVerticalStrut(75));
        add(resultTextField());
        add(Box.createVerticalStrut(75));
        add(createCurrencyToolbar());
    }

    private JPanel resultTextField() {
        JPanel panel = new CustomizedComponent().customizePanel(new JPanel());
        panel.add(this.amountField = new CustomizedComponent().customizeTextField(new JTextField()));
        this.amountField.setEditable(false);
        return panel;
    }

    private SwingCurrencyDialog createCurrencyDialog() {
        return new SwingCurrencyDialog();
    }

    private JPanel createCurrencyToolbar() {
        currencyToolbar = new JPanel();
        currencyToolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        currencyToolbar.setOpaque(false);
        return currencyToolbar;
    }

    public SwingMoneyDisplay addButtonsToToolbar(JButton... buttons) {
        for (JButton button : buttons) {
            currencyToolbar.add(button);
        }
        revalidate();
        repaint();
        return this;
    }

    public MoneyDisplay define(Map<String, Currency> currencies) {
        this.currencyDialog.define(currencies);
        return this;
    }

    public CurrencyDialog getCurrencyDialog() {
        return this.currencyDialog;
    }

    private double toDouble(String text) {
        return text.isEmpty() ? 0 : Double.parseDouble(text);
    }

    @Override
    public Money get() {
        return new Money(toDouble(amountField.getText()), currencyDialog.get());
    }

    @Override
    public MoneyDisplay set(Money money) {
        amountField.setText(String.format(Locale.US, "%.2f", money.amount()));
        return this;
    }
}

