public class InsufficientFundsException extends BankException {
    private final double shortBy;

    public InsufficientFundsException(String accountId, double shortBy) {
        super(String.format("Account %s is short by $%.2f", accountId, shortBy));
        this.shortBy = shortBy;
    }

    public double getShortBy() { return shortBy; }
}
