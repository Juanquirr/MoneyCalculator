package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.control.*;
import software.ulpgc.moneycalculator.io.CurrencyDialog;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.SwingCurrencyDialog;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Currency> currencies = new CustomCurrencyLoader().load();
        SwingMainFrame swingMainFrame = new SwingMainFrame();
        swingMainFrame.defineCurrencies(new CustomCurrencyLoader().load()).add("change format", new FormatCommand(
                getCurrencyDialogFromMoneyDialog(swingMainFrame),
                getCurrencyDialogFromMoneyDisplay(swingMainFrame)))
                .add("clear", new ClearCommand(swingMainFrame.getMoneyDialog(), swingMainFrame.getMoneyDisplay(), currencies))
                .add("exchange rate", new ExchangeMoneyCommand(swingMainFrame.getMoneyDialog(), swingMainFrame.getMoneyDisplay(),
                new MockExchangeRateLoader(currencies).load()))
                .add("pound dialog", new GBPDialogCommand((SwingCurrencyDialog) getCurrencyDialogFromMoneyDialog(swingMainFrame), currencies))
                .add("dollar dialog", new USDDialogCommand((SwingCurrencyDialog) getCurrencyDialogFromMoneyDialog(swingMainFrame), currencies))
                .add("euro dialog", new EURDialogCommand((SwingCurrencyDialog) getCurrencyDialogFromMoneyDialog(swingMainFrame), currencies))
                .add("pound display", new GBPDisplayCommand((SwingCurrencyDialog) getCurrencyDialogFromMoneyDisplay(swingMainFrame), currencies))
                .add("dollar display", new USDDisplayCommand((SwingCurrencyDialog) getCurrencyDialogFromMoneyDisplay(swingMainFrame), currencies))
                .add("euro display", new EURDisplayCommand((SwingCurrencyDialog) getCurrencyDialogFromMoneyDisplay(swingMainFrame), currencies));
        swingMainFrame.setVisible(true);//

        // new CustomExchangeRateLoader(currencies).load())) PRO MODE
    }

    private static CurrencyDialog getCurrencyDialogFromMoneyDisplay(SwingMainFrame swingMainFrame) {
        return swingMainFrame.getMoneyDisplay().getCurrencyDialog();
    }

    private static CurrencyDialog getCurrencyDialogFromMoneyDialog(SwingMainFrame swingMainFrame) {
        return swingMainFrame.getMoneyDialog().getCurrencyDialog();
    }
}
