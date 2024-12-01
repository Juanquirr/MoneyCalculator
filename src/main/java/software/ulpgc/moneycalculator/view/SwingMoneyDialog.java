package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.io.MoneyDialog;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.io.CurrencyDialog;
import software.ulpgc.moneycalculator.model.Money;

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
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(new LineBorder(Color.blue, 3));
        panel.setBackground(new Color(90, 121, 200));
        panel.setPreferredSize(new Dimension(280, 40));
        panel.setBackground(Color.white);
        panel.add(this.amountField = new CustomizedComponent().customizeTextField(new SwingAmountEntry()));
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

    public void addButtonsToToolbar(JButton... buttons) {
        for (JButton button : buttons) {
            currencyToolbar.add(button);
        }
        revalidate();
        repaint();
    }

    @Override
    public Money get() {
        return new Money(toLong(amountField.getText()), currencyDialog.get());
    }

    @Override
    public MoneyDialog set(Money money) {
        amountField.setText(String.valueOf(money.amount()));
        System.out.println(money.amount());
        return this;
    }

    private long toLong(String text) {
        return text.equals("Enter amount") ? 0 : Long.parseLong(text);
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }
}



