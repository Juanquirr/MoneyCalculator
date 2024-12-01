package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.util.Map;

public interface ExchangeRateLoader {
    Map<Currency, ExchangeRate> load() throws IOException;
}
