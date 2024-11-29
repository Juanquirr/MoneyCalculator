package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.io.CurrencyDialog;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.io.MoneyDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;


public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(90, 121, 200));
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        add(createCurrencyDialog(currencies));
        add(Box.createVerticalStrut(100));
        add(moneyDialogTextField());
        add(Box.createVerticalStrut(30));
        add(createCurrencyToolbar());
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

    private SwingCurrencyDialog createCurrencyDialog(List<Currency> currencies) {
        this.currencyDialog = new SwingCurrencyDialog().define(currencies);
        return (SwingCurrencyDialog) this.currencyDialog;
    }

    private JPanel createCurrencyToolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.setOpaque(false);
        currencyButtons(toolbar);
        return toolbar;
    }

    private void currencyButtons(JPanel toolbar) {
        JButton gbp = new CustomizedComponent().customizeButton(new JButton("GBP"));
        JButton usd = new CustomizedComponent().customizeButton(new JButton("USD"));
        JButton eur = new CustomizedComponent().customizeButton(new JButton("EUR"));

        gbp.addActionListener(e -> System.out.println("LIBRA"));
        usd.addActionListener(e -> System.out.println("DÓLAR"));
        eur.addActionListener(e -> System.out.println("EURO"));

        toolbar.add(gbp);
        toolbar.add(usd);
        toolbar.add(eur);
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


