package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.CurrencyDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private JComboBox<Currency> currencySelector;

    public SwingCurrencyDialog() {
        setLayout(new FlowLayout());
        setBorder(new LineBorder(Color.magenta));
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        add(createCurrencySelector(currencies));
        return this;
    }

    private Component createCurrencySelector(List<Currency> currencies) {
        JComboBox<Currency> selector = new JComboBox<>();
        for (Currency currency : currencies) selector.addItem(currency);
        this.currencySelector = selector;
        return selector;
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());
    }
}
