package software.ulpgc.moneycalculator.apps.windows.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericFilter extends DocumentFilter {

    private String placeholder = "";

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), string))
            super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), text))
            super.replace(fb, offset, length, text, attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isValidInput(String currentText, String newText) {
        if (newText.equals(placeholder)) return true;
        if ((currentText.contains(".") && newText.equals(".")) ||
                (currentText.isEmpty() && newText.equals(".")) ||
                (currentText.matches(".*\\.\\d{4}") && newText.matches("\\d")))
            return false;
        return isNumeric(newText);
    }


    private boolean isNumeric(String text) {
        return text.matches("(\\d|\\.|\\d+\\.\\d|)");
    }
}
