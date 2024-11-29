package software.ulpgc.moneycalculator.model;

public record Currency(String code, String name) implements Comparable<Currency> {
    @Override
    public int compareTo(Currency o) {
        return code.compareTo(o.code);
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}' + '\b';
    }
}