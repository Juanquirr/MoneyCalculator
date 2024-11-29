package software.ulpgc.moneycalculator.apps.pojo;

import java.util.List;

public record PojoCurrencies(Symbol symbol){

    public record Symbol(List<Coin> coins) {}
    public record Coin(String code, String name) {}
}
