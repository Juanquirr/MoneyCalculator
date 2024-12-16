package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.apps.windows.customization.CustomizedComponent;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.CurrencyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private JComboBox<Currency> currencySelector;
    private Map<String, Currency> currencies;

    public SwingCurrencyDialog() {
        setLayout(new FlowLayout());
        setOpaque(false);
    }

    @Override
    public CurrencyDialog define(Map<String, Currency> currencies) {
        this.currencies = new TreeMap<>(currencies);
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
        currencies.values().forEach(currency -> currencySelector.addItem(currency));
        changeCurrencyFormat(SwingCurrencyRenderer.Format.NAME);
    }

    public void changeCurrencyFormat(SwingCurrencyRenderer.Format format) {
        currencySelector.setRenderer(new SwingCurrencyRenderer(format));
        repaint();
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());}

    @Override
    public CurrencyDialog set(Currency currency) {
        this.currencySelector.setSelectedItem(currency);
        return this;
    }
}
