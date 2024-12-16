package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.apps.windows.view.SwingCurrencyRenderer;
import software.ulpgc.moneycalculator.apps.windows.view.SwingCurrencyDialog;
import software.ulpgc.moneycalculator.view.CurrencyDialog;

public class FormatCommand implements Command {
    private final CurrencyDialog swingCurrencyDialogLeft;
    private final CurrencyDialog swingCurrencyDialogRight;
    private int i = 0;

    public FormatCommand(CurrencyDialog swingCurrencyDialogLeft, CurrencyDialog swingCurrencyDialogRight) {
        if (swingCurrencyDialogLeft == null || swingCurrencyDialogRight == null)
            throw new IllegalArgumentException("Currency dialogs can't be null.");
        this.swingCurrencyDialogLeft = swingCurrencyDialogLeft;
        this.swingCurrencyDialogRight = swingCurrencyDialogRight;
    }

    @Override
    public void execute() {
        ((SwingCurrencyDialog) swingCurrencyDialogLeft).changeCurrencyFormat(i == 0 ?
                SwingCurrencyRenderer.Format.CODE : SwingCurrencyRenderer.Format.NAME);
        ((SwingCurrencyDialog) swingCurrencyDialogRight).changeCurrencyFormat(i == 0 ?
                SwingCurrencyRenderer.Format.CODE : SwingCurrencyRenderer.Format.NAME);
        i = ++i % 2;
    }
}
