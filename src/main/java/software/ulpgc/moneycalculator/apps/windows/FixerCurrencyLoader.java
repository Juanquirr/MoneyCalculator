package software.ulpgc.moneycalculator.apps.windows;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.CurrencyLoader;
import software.ulpgc.moneycalculator.model.TsvFileCurrencyLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class FixerCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
//        try {
//            return toList(loadJson());
//        } catch (IOException e) {
//            return emptyList();
//        }

        return new TsvFileCurrencyLoader(new File("currencies.tsv")).load();
    }

//    private List<Currency> toList(String json) {
//        List<Currency> list = new ArrayList<>();
//        Map<String, JsonElement> symbols = new Gson().fromJson(json, JsonObject.class).get("symbols").getAsJsonObject().asMap();
//        for (String symbol : symbols.keySet())
//            list.add(new Currency(symbol, symbols.get(symbol).getAsString()));
//        return list;
//    }
//
//    private String loadJson() throws IOException {
//        URL url = new URL("http://data.fixer.io/api/symbols?access_key=" + FixerAPI.key);
//        try (InputStream is = url.openStream()) {
//            return new String(is.readAllBytes());
//        }
//    }
}
