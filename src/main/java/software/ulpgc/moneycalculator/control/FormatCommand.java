package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.io.CurrencyDialog;
import software.ulpgc.moneycalculator.view.*;

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
                CurrencyRenderer.Format.CODE : CurrencyRenderer.Format.NAME);
        ((SwingCurrencyDialog) swingCurrencyDialogRight).changeCurrencyFormat(i == 0 ?
                CurrencyRenderer.Format.CODE : CurrencyRenderer.Format.NAME);
        i = ++i % 2;
    }
}
