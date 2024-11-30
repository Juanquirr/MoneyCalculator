package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.io.CurrencyDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private JComboBox<String> currencySelector;
    private List<Currency> currencies;
    private Format currentFormat = Format.CODE_AND_NAME;

    public SwingCurrencyDialog() {
        setLayout(new FlowLayout());
        setBorder(new LineBorder(Color.magenta));
        setOpaque(false);
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        this.currencies = currencies;
        add(createCurrencySelector());
        return this;
    }

    private Component createCurrencySelector() {
        this.currencySelector = new CustomizedComponent().customizeComboBox(new JComboBox<>());
        updateCurrencySelector();
        return currencySelector;
    }

    private void updateCurrencySelector() {
        currencySelector.removeAllItems();
        for (Currency currency : currencies) currencySelector.addItem(formatCurrency(currency, currentFormat));
    }

    public void switchFormat() {
        currentFormat = currentFormat.next();
        updateCurrencySelector();
    }

    private String formatCurrency(Currency currency, Format format) {
        return switch (format) {
            case CODE -> currency.code();
            case NAME -> currency.name();
            case CODE_AND_NAME -> currency.code() + " - " + currency.name();
        };
    }

    @Override
    public Currency get() {
        int index = currencySelector.getSelectedIndex();
        return index >= 0 ? currencies.get(index) : null;
    }

    private enum Format {
        CODE, NAME, CODE_AND_NAME;

        public Format next() {
            return values()[(ordinal() + 1) % values().length];
        }
    }
}
