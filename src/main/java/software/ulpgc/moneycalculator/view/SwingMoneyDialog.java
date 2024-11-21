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
        this.setBorder(new LineBorder(Color.black));
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createLabelAndAmountField());
//        add(Box.createRigidArea(new Dimension(0, 50)));
        add(createCurrencyDialog(currencies));
        return this;
    }

    private Component createLabelAndAmountField() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new LineBorder(Color.RED, 2)); // RECUERDA BORRAR
        panel.setMaximumSize(new Dimension(300, 50));
        JLabel label = new JLabel("Introduce an amount: ");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(10, 200, 100));

        JTextField textField = new JTextField("0");
        textField.setPreferredSize(new Dimension(100, 25));
        this.amountField = textField;

        panel.add(label);
        panel.add(textField);
        return panel;
    }


    private Component createCurrencyDialog(List<Currency> currencies) {
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
}
