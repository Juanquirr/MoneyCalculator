package software.ulpgc.moneycalculator.apps.windows.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.io.CurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomCurrencyLoader implements CurrencyLoader {
    @Override
    public Map<String, Currency> load() throws IOException {
        return loadCurrencies();
    }

    public Map<String, Currency> loadCurrencies() throws IOException {
        String json = read();
        Map<String, JsonElement> coins = new Gson().fromJson(json, JsonObject.class).get("symbols").getAsJsonObject().asMap();
        return coins.entrySet().stream().collect(Collectors
                .toMap(Map.Entry::getKey,
                        e-> new Currency(e.getKey(), e.getValue().getAsString())));
    }

    private static String read() throws IOException {
        try (InputStream is = CustomCurrencyLoader.class.getResourceAsStream("/symbols.json")) {
            if (is != null) return new String(is.readAllBytes());
        }
        return "";
    }
}
