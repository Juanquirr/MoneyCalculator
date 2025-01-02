package software.ulpgc.moneycalculator.apps.windows.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import javax.swing.*;
import java.awt.*;

public class SwingCurrencyRenderer extends JLabel implements ListCellRenderer<Currency> {

    private final Format format;

    public SwingCurrencyRenderer(Format format) {
        this.format = format;
        this.setOpaque(true);
    }

    public enum Format {
        CODE, NAME
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Currency> list, Currency value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setText(format == Format.CODE ? value.code() : value.name());
        return this;
    }
}
