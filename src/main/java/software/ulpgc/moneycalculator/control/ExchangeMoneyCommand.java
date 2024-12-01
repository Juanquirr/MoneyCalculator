package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.io.MoneyDialog;
import software.ulpgc.moneycalculator.model.*;

import java.util.Map;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog fromMoneyDialog;
    private final MoneyDialog toMoneyDialog;
    private final Map<Currency, ExchangeRate> exchangeRateLoader;

    public ExchangeMoneyCommand(MoneyDialog fromMoneyDialog, MoneyDialog toMoneyDialog, Map<Currency, ExchangeRate> exchangeRateLoader) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.toMoneyDialog = toMoneyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
    }

    @Override
    public void execute() {
        long amount = fromMoneyDialog.get().amount();
        Currency currencyFrom = fromMoneyDialog.get().currency();
        Currency currencyTo = toMoneyDialog.get().currency();
        Money money = new Money((long) ((long) ((amount / exchangeRateLoader.get(currencyFrom).rate())) * exchangeRateLoader.get(currencyTo).rate()), currencyTo);
        toMoneyDialog.set(money);
    }
}
