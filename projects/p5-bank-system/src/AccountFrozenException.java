public class AccountFrozenException extends BankException {
    public AccountFrozenException(String accountId) {
        super("Account " + accountId + " is frozen after too many failed PIN attempts");
    }
}
