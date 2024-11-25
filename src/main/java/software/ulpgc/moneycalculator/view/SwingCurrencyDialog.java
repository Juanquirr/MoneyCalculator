package software.ulpgc.moneycalculator.view;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.CurrencyDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private JComboBox<Currency> currencySelector;
    private final String label;

    public SwingCurrencyDialog(String label) {
        this.label = label;
        setLayout(new FlowLayout());
        setBorder(new LineBorder(Color.magenta));
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        add(getLabel());
        add(createCurrencySelector(currencies));
        return this;
    }

    private JLabel getLabel() {
        JLabel fromToLabel = new JLabel(this.label);
        fromToLabel.setFont(new Font("Arial", Font.BOLD, 12));
        return fromToLabel;
    }

    private Component createCurrencySelector(List<Currency> currencies) {
        JComboBox<Currency> selector = new JComboBox<>();
        for (Currency currency : currencies) selector.addItem(currency);
        selector.setBackground(new Color(102, 150, 200));
        selector.setBorder(new LineBorder(Color.black, 3));
        this.currencySelector = selector;
        return selector;
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());
    }
}
