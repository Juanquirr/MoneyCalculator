package software.ulpgc.moneycalculator.view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class SwingAmountEntry extends JTextField {
    public SwingAmountEntry() {
        setColumns(10);
        setText("0.00");
        setBackground(Color.lightGray);
        setForeground(Color.white);
        setBorder(null);
        setMaximumSize(new Dimension(240, 20));
        ((AbstractDocument) getDocument()).setDocumentFilter(new NumericFilter());
    }
}
