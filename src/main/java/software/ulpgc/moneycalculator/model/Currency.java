package software.ulpgc.moneycalculator.model;

import java.util.Objects;

public class Currency {
    private final String code;
    private final String name;
//    private final String symbol;

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
//        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

//    public String getSymbol() {
//        return symbol;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code) && Objects.equals(name, currency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}' + '\b';
    }
}