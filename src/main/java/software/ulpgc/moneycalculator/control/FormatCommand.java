package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.io.CurrencyDialog;
import software.ulpgc.moneycalculator.view.*;

public class FormatCommand implements Command {
    private final CurrencyDialog swingCurrencyDialogLeft;
    private final CurrencyDialog swingCurrencyDialogRight;
    private int i = 0;

    public FormatCommand(CurrencyDialog swingCurrencyDialogLeft, CurrencyDialog swingCurrencyDialogRight) {
        if (swingCurrencyDialogLeft == null || swingCurrencyDialogRight == null) {
            throw new IllegalArgumentException("Los diálogos de moneda no pueden ser nulos.");
        }
        this.swingCurrencyDialogLeft = swingCurrencyDialogLeft;
        this.swingCurrencyDialogRight = swingCurrencyDialogRight;
    }

    @Override
    public void execute() {
        i = ++i % 2;
        ((SwingCurrencyDialog) swingCurrencyDialogLeft).changeCurrencyFormat(i != 0 ? CurrencyRenderer.Format.NAME : CurrencyRenderer.Format.CODE);
        ((SwingCurrencyDialog) swingCurrencyDialogRight).changeCurrencyFormat(i != 0 ? CurrencyRenderer.Format.NAME : CurrencyRenderer.Format.CODE);

    }
}
