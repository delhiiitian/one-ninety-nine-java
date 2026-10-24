import java.util.LinkedHashMap;
import java.util.Map;

// Holds all accounts and performs operations that involve more than one account.
public class Bank {
    private final Map<String, Account> accounts = new LinkedHashMap<>();
    private int nextNumber = 1001;

    public Account open(String owner, String pin, double opening) {
        String id = "AC" + nextNumber++;
        Account a = new Account(id, owner, pin, opening);
        accounts.put(id, a);
        return a;
    }

    public Account find(String id) throws AccountNotFoundException {
        Account a = accounts.get(id);
        if (a == null) throw new AccountNotFoundException(id);
        return a;
    }

    // All-or-nothing: if the withdrawal fails, the deposit never happens
    public void transfer(String fromId, String toId, double amount) throws BankException {
        Account from = find(fromId);
        Account to = find(toId);
        if (from == to) throw new BankException("Cannot transfer to the same account");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (amount > from.getBalance()) throw new InsufficientFundsException(fromId, amount - from.getBalance());
        from.adjust(-amount);
        to.adjust(amount);
        from.recordTransfer("TRANSFER>" , amount);
        to.recordTransfer("TRANSFER<", amount);
    }

    public Map<String, Account> all() { return accounts; }
}
