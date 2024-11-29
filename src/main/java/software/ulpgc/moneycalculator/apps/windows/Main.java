package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.apps.mock.MockExchangeRateLoader;
import software.ulpgc.moneycalculator.control.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.view.SwingMainFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingMainFrame swingMainFrame = new SwingMainFrame();
//        swingMainFrame.put("calculate", new() );
//        swingMainFrame.put("change format", new() );
        swingMainFrame.add("exchange money", new ExchangeMoneyCommand(
                swingMainFrame.getMoneyDialogLeft(),
                new MockExchangeRateLoader(),
                swingMainFrame.moneyDisplay()));
        swingMainFrame.setVisible(true);
    }
}
