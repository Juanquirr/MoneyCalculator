package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.io.MoneyDialog;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;

import java.util.Map;

public class ClearCommand implements Command {
    private final MoneyDialog fromMoneyDialog;
    private final MoneyDialog toMoneyDialog;
    private final Map<String, Currency> currencies;


    public ClearCommand(MoneyDialog fromMoneyDialog, MoneyDialog toMoneyDialog, Map<String, Currency> currencies) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.toMoneyDialog = toMoneyDialog;
        this.currencies = currencies;
    }

    @Override
    public void execute() {
        fromMoneyDialog.set(new Money(0, currencies.get("EUR")));
        toMoneyDialog.set(new Money(0, currencies.get("EUR")));
    }
}
