package software.ulpgc.moneycalculator.apps.mock;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.io.CurrencyLoader;
import software.ulpgc.moneycalculator.io.TsvFileCurrencyLoader;

import java.io.File;
import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        return new TsvFileCurrencyLoader(new File("currencies.tsv")).load();
    }
}
