package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.model.*;
import software.ulpgc.moneycalculator.view.SwingMoneyDialog;

public class CalculateCommand implements Command {
    private Money money;
    private SwingMoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;
    private MoneyDisplay moneyDisplay;
    private ExchangeRate exchangeRate;

    public CalculateCommand(Money money, SwingMoneyDialog moneyDialog, CurrencyDialog currencyDialog, MoneyDisplay moneyDisplay, ExchangeRate exchangeRate) {
        this.money = money;
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public void execute() {
        moneyDisplay.show(money);
    }
}
