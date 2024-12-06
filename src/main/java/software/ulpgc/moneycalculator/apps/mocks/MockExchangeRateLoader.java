package software.ulpgc.moneycalculator.apps.mocks;

import software.ulpgc.moneycalculator.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    private final Map<String, Currency> currencies;

    public MockExchangeRateLoader(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Map<Currency, ExchangeRate> load() {
        return currencies.values().stream()
                .collect(Collectors.toMap(
                        e -> e,
                        e -> new ExchangeRate(currencies.get("EUR"), e, LocalDate.now(), 1)
                ));
    }
}
