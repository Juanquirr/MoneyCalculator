package software.ulpgc.moneycalculator.apps.windows;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.apps.pojo.PojoCurrencies;

import java.io.IOException;
import java.io.InputStream;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
//        CurrencyLoader currencyLoader = new FixerCurrencyLoader();
//        List<Currency> currencies = currencyLoader.load();
//        for (Currency currency : currencies) {
//            System.out.println(currency);
//        }

//        PojoCurrencies coins = new Gson().fromJson(json, PojoCurrencies.class);
//        System.out.println(coins);
//        JsonElement jsonElement = new Gson().fromJson(json, JsonElement.class);
        String json = read();
        Map<String, JsonElement> coins = new Gson().fromJson(json, JsonObject.class).get("symbols").getAsJsonObject().asMap();
        Map<String, String> map = new HashMap<>();
        coins.forEach((symbol, jsonElement) -> {map.put(symbol,jsonElement.getAsString());});
        for (String s : map.keySet()) {
            System.out.println(s + ": " + map.get(s));
        }
    }

    private static String read() throws IOException {
        try (InputStream is = Main.class.getResourceAsStream("/symbols.json")) {
            return new String(is.readAllBytes());
        }
    }


}
