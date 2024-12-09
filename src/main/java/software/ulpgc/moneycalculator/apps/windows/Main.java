package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.control.*;
import software.ulpgc.moneycalculator.io.CurrencyDialog;
import software.ulpgc.moneycalculator.model.Currency;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, Currency> currencies;
    private static SwingMainFrame frame;

    public static void main(String[] args) throws IOException {
        createMainFrame().defineCurrencies(loadCurrencies())
                .add(generateCommands())
                .setVisible(true);
    }

    private static SwingMainFrame createMainFrame() {
        return frame = new SwingMainFrame().initializeFrame();
    }

    private static Map<String, Currency> loadCurrencies() throws IOException {
        return currencies = new CustomCurrencyLoader().load();
    }

    private static Map<String, Command> generateCommands() throws IOException {
        Map<String, Command> commands = new HashMap<>();
        commands.put("change format", new FormatCommand(
                        getCurrencyDialogFromMoneyDialog(),
                        getCurrencyDialogFromMoneyDisplay()));
        commands.put("clear", new ClearCommand(frame.getMoneyDialog(), frame.getMoneyDisplay(), currencies));
        commands.put("exchange rate", new ExchangeMoneyCommand(frame.getMoneyDialog(), frame.getMoneyDisplay(),
//                new MockExchangeRateLoader(currencies).load()));
                        new CustomExchangeRateLoader(currencies).load()));
        commands.put("pound dialog", new GBPDialogCommand(getCurrencyDialogFromMoneyDialog(), currencies));
        commands.put("dollar dialog", new USDDialogCommand(getCurrencyDialogFromMoneyDialog(), currencies));
        commands.put("euro dialog", new EURDialogCommand(getCurrencyDialogFromMoneyDialog(), currencies));
        commands.put("pound display", new GBPDisplayCommand(getCurrencyDialogFromMoneyDisplay(), currencies));
        commands.put("dollar display", new USDDisplayCommand(getCurrencyDialogFromMoneyDisplay(), currencies));
        commands.put("euro display", new EURDisplayCommand(getCurrencyDialogFromMoneyDisplay(), currencies));
        return commands;
    }

    private static CurrencyDialog getCurrencyDialogFromMoneyDisplay() {
        return frame.getMoneyDisplay().getCurrencyDialog();
    }

    private static CurrencyDialog getCurrencyDialogFromMoneyDialog() {
        return frame.getMoneyDialog().getCurrencyDialog();
    }
}
