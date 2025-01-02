package software.ulpgc.moneycalculator.architecture.io;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.io.IOException;
import java.util.Map;

public interface ExchangeRateLoader {
    Map<Currency, ExchangeRate> load() throws IOException;
}
