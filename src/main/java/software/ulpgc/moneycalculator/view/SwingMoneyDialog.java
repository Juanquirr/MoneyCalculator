package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.CurrencyDialog;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.model.MoneyDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new LineBorder(Color.pink, 3));
        this.setBackground(new Color(121,4, 235));
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createCurrencyDialog(currencies));
        add(Box.createVerticalStrut(50));
        add(moneyDialogTextField());
        return this;
    }

    private JTextField moneyDialogTextField() {
        JTextField textField = new JTextField("0");
        textField.setPreferredSize(new Dimension(100, 25));
        this.amountField = textField;
        return textField;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        //this.currencyDialog = new SwingCurrencyDialog().define(currencies);
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.currencyDialog = dialog;
        return dialog;
    }

    @Override
    public Money get() {
        return new Money(toLong(amountField.getText()), currencyDialog.get());
    }

    private long toLong(String text) {
        return Long.parseLong(text);
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }
}
