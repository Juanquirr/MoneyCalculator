package software.ulpgc.moneycalculator.apps.windows;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.model.ExchangeRate;
import software.ulpgc.moneycalculator.model.Currency;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
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
        return toMap(loadJson());
    }

    private Map<Currency, ExchangeRate> toMap(String json) {
        HashMap<Currency, ExchangeRate> map = new HashMap<>();
        Currency base = currencies.get(new Gson().fromJson(json, JsonObject.class).get("base").getAsString());
        String date = new Gson().fromJson(json, JsonObject.class).get("date").getAsString();
        String[] split = date.split("-");
        Map<String, JsonElement> exchangeRates = new Gson().fromJson(json, JsonObject.class).get("rates").getAsJsonObject().asMap();
        return exchangeRates.entrySet().stream().collect(Collectors
                .toMap(e -> currencies.get(e.getKey()),
                        e -> new ExchangeRate(base,
                                currencies.get(e.getKey()), LocalDate.of(parseInt(split[0]), parseInt(split[1]), parseInt(split[2])),
                                e.getValue().getAsDouble())
                ));
    }

    private String loadJson() throws IOException {
        URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=" + API.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
