package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.view.MoneyDialog;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.view.MoneyDisplay;

import java.util.Map;

public class ClearCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final Map<String, Currency> currencies;


    public ClearCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, Map<String, Currency> currencies) {
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.currencies = currencies;
    }

    @Override
    public void execute() {
        moneyDialog.set(new Money(0, currencies.get("EUR")));
        moneyDisplay.set(new Money(0, currencies.get("EUR")));
    }
}
