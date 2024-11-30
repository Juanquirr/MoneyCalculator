package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.io.MoneyDialog;
import software.ulpgc.moneycalculator.view.*;

public class FormatCommand implements Command {
    private final MoneyDialog moneyDialogLeft;
    private final MoneyDialog moneyDialogRight;

    public FormatCommand(MoneyDialog moneyDialogLeft, MoneyDialog moneyDialogRight) {
        this.moneyDialogLeft = moneyDialogLeft;
        this.moneyDialogRight = moneyDialogRight;
    }

    @Override
    public void execute() {
        ((SwingCurrencyDialog) moneyDialogLeft).switchFormat();
        ((SwingCurrencyDialog) moneyDialogRight).switchFormat();
    }
}
