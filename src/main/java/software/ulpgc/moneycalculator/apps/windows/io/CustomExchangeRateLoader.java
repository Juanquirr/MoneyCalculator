package software.ulpgc.moneycalculator.apps.windows.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.apps.windows.API;
import software.ulpgc.moneycalculator.architecture.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class CustomExchangeRateLoader implements ExchangeRateLoader {

    private final Map<String, Currency> currencies;

    public CustomExchangeRateLoader(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Map<Currency, ExchangeRate> load() throws IOException {
        return toMap(loadJsonFromAPI());
    }

    private Map<Currency, ExchangeRate> toMap(String json) {
        Currency baseCurrency = currencies.get(new Gson().fromJson(json, JsonObject.class).get("base").getAsString());
        String[] arrayOfDate = new Gson().fromJson(json, JsonObject.class).get("date").getAsString().split("-");
        Map<String, JsonElement> exchangeRates = new Gson().fromJson(json, JsonObject.class).get("rates").getAsJsonObject().asMap();
        return exchangeRates.entrySet().stream().collect(Collectors
                .toMap(e -> currencies.get(e.getKey()),
                        e -> new ExchangeRate(baseCurrency,
                                currencies.get(e.getKey()), convertToLocaldate(arrayOfDate),
                                e.getValue().getAsFloat())
                ));
    }

    private static LocalDate convertToLocaldate(String[] split) {
        return LocalDate.of(parseInt(split[0]), parseInt(split[1]), parseInt(split[2]));
    }

    private String loadJsonFromAPI() throws IOException {
        URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=" + API.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
