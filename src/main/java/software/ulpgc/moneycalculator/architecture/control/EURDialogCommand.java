package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.view.CurrencyDialog;
import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.Map;

public class EURDialogCommand implements Command {
    private final CurrencyDialog swingCurrencyDialog;
    private final Map<String, Currency> currencies;

    public EURDialogCommand(CurrencyDialog swingCurrencyDialog, Map<String, Currency> currencies) {
        this.swingCurrencyDialog = swingCurrencyDialog;
        this.currencies = currencies;
    }

    @Override
    public void execute() {
        swingCurrencyDialog.set(currencies.get("EUR"));
    }
}
