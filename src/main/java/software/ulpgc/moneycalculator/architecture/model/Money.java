package software.ulpgc.moneycalculator.architecture.model;

public record Money(double amount, Currency currency) {

    @Override
    public String toString() {
        return amount + " " + currency;
    }

}
