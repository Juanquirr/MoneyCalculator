package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.io.MoneyDialog;
import software.ulpgc.moneycalculator.model.*;
import software.ulpgc.moneycalculator.view.MoneyDisplay;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog fromMoneyDialog;
    //private final MoneyDialog toMoneyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeMoneyCommand(MoneyDialog fromMoneyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        Money money = fromMoneyDialog.get();
        Currency currency = fromMoneyDialog.get().currency();

        ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), currency);
        Money result = new Money((long) (money.amount()*exchangeRate.rate()), currency);

        moneyDisplay.show(result);
    }
}
