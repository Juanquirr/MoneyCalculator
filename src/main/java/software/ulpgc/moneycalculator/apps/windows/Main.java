package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.control.*;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.SwingCurrencyDialog;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Currency> currencies = new CustomCurrencyLoader().load();
        SwingMainFrame swingMainFrame = new SwingMainFrame();
        swingMainFrame.defineCurrencies(new CustomCurrencyLoader().load())
        .add("change format", new FormatCommand(
                swingMainFrame.getMoneyDialogLeft().getCurrencyDialog(),
                swingMainFrame.getMoneyDialogRight().getCurrencyDialog()))
        .add("exchange rate", new ExchangeMoneyCommand(
                swingMainFrame.getMoneyDialogLeft(),
                swingMainFrame.getMoneyDialogRight(),
                new MockExchangeRateLoader(currencies).load()))
                //                new CustomExchangeRateLoader(currencies).load()))
                .add("pound", new GBPSelectorCommand((SwingCurrencyDialog) swingMainFrame.getMoneyDialogLeft().getCurrencyDialog(),
                currencies))
                .add("dollar", new USDSelectorCommand((SwingCurrencyDialog) swingMainFrame.getMoneyDialogLeft().getCurrencyDialog(),
                        currencies))
                .add("euro", new EURSelectorCommand((SwingCurrencyDialog) swingMainFrame.getMoneyDialogLeft().getCurrencyDialog(),
                        currencies))
                .add("clear", new ClearCommand(swingMainFrame.getMoneyDialogLeft(),
                        swingMainFrame.getMoneyDialogRight(), currencies))
                .setVisible(true);
    }
}
