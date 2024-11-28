package software.ulpgc.moneycalculator.io;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.CurrencyLoader;
import software.ulpgc.moneycalculator.model.TsvFileCurrencyLoader;

import java.io.File;
import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        return new TsvFileCurrencyLoader(new File("currencies.tsv")).load();
    }
}
