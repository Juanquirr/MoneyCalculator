package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.SwingCurrencyDialog;

import java.util.Map;

public class USDDialogCommand implements Command {
    private final SwingCurrencyDialog swingCurrencyDialog;
    private final Map<String, Currency> currencies;

    public USDDialogCommand(SwingCurrencyDialog swingCurrencyDialog, Map<String, Currency> currencies) {
        this.swingCurrencyDialog = swingCurrencyDialog;
        this.currencies = currencies;
    }

    @Override
    public void execute() {
        swingCurrencyDialog.set(currencies.get("USD"));
    }
}
