package software.ulpgc.moneycalculator.view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SwingAmountEntry extends JTextField {

    private static final String PLACEHOLDER = "Enter amount";

    public SwingAmountEntry() {
        super(PLACEHOLDER);

        NumericFilter numericFilter = new NumericFilter();
        numericFilter.setPlaceholder(PLACEHOLDER);
        ((AbstractDocument) getDocument()).setDocumentFilter(numericFilter);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(PLACEHOLDER)) setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().trim().isEmpty()) setText(PLACEHOLDER);
            }
        });
    }
}
