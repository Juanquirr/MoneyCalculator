package software.ulpgc.moneycalculator.architecture.control;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.model.Money;
import software.ulpgc.moneycalculator.architecture.view.MoneyDialog;
import software.ulpgc.moneycalculator.architecture.view.MoneyDisplay;
import software.ulpgc.moneycalculator.apps.windows.view.SwingMoneyDisplay;

import java.util.Map;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog fromMoneyDialog;
    private final MoneyDisplay toMoneyDialog;
    private final Map<Currency, ExchangeRate> exchangeRateLoader;

    public ExchangeMoneyCommand(MoneyDialog fromMoneyDialog, SwingMoneyDisplay toMoneyDialog, Map<Currency, ExchangeRate> exchangeRateLoader) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.toMoneyDialog = toMoneyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
    }

    @Override
    public void execute() {
        double amount = fromMoneyDialog.get().amount();
        Currency currencyFrom = fromMoneyDialog.get().currency();
        Currency currencyTo = toMoneyDialog.get().currency();

        double rateFrom = exchangeRateLoader.get(currencyFrom).rate();
        double rateTo = exchangeRateLoader.get(currencyTo).rate();

        double convertedAmount = (amount / rateFrom) * rateTo;

        Money money = new Money(convertedAmount, currencyTo);
        toMoneyDialog.set(money);
    }

}
