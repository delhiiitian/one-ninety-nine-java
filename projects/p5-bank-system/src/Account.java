import java.util.ArrayList;
import java.util.List;

// One bank account. Bad requests are refused with exceptions, so the balance can never become invalid.
public class Account {
    private static final int MAX_PIN_ATTEMPTS = 3;
    private static final double DAILY_WITHDRAWAL_LIMIT = 1000;

    private final String id;
    private final String owner;
    private final String pin;
    private double balance;
    private double withdrawnToday;
    private int failedPins;
    private final List<String> statement = new ArrayList<>();

    public Account(String id, String owner, String pin, double opening) {
        if (opening < 0) throw new IllegalArgumentException("Opening balance cannot be negative");
        if (pin == null || !pin.matches("\\d{4}")) throw new IllegalArgumentException("PIN must be 4 digits");
        this.id = id;
        this.owner = owner;
        this.pin = pin;
        this.balance = opening;
        statement.add(String.format("OPEN       %9.2f  balance %9.2f", opening, balance));
    }

    public void verifyPin(String attempt) throws BankException {
        if (failedPins >= MAX_PIN_ATTEMPTS) throw new AccountFrozenException(id);
        if (!pin.equals(attempt)) {
            failedPins++;
            if (failedPins >= MAX_PIN_ATTEMPTS) throw new AccountFrozenException(id);
            throw new BankException("Wrong PIN, " + (MAX_PIN_ATTEMPTS - failedPins) + " attempt(s) left");
        }
        failedPins = 0;
    }

    public void deposit(double amount) {
        requirePositive(amount);
        balance += amount;
        statement.add(String.format("DEPOSIT    %9.2f  balance %9.2f", amount, balance));
    }

    public void withdraw(double amount) throws BankException {
        requirePositive(amount);
        if (failedPins >= MAX_PIN_ATTEMPTS) throw new AccountFrozenException(id);
        if (amount > balance) throw new InsufficientFundsException(id, amount - balance);
        if (withdrawnToday + amount > DAILY_WITHDRAWAL_LIMIT) {
            throw new BankException(String.format("Daily limit reached: $%.2f left today", DAILY_WITHDRAWAL_LIMIT - withdrawnToday));
        }
        balance -= amount;
        withdrawnToday += amount;
        statement.add(String.format("WITHDRAW   %9.2f  balance %9.2f", amount, balance));
    }

    // Used by transfers: a record line without daily-limit rules
    void recordTransfer(String label, double amount) {
        statement.add(String.format("%-10s %9.2f  balance %9.2f", label, amount, balance));
    }

    void adjust(double delta) { balance += delta; }

    private static void requirePositive(double amount) {
        // Unchecked: a non-positive amount is a programming or input mistake, not a business rule
        if (amount <= 0 || Double.isNaN(amount)) throw new IllegalArgumentException("Amount must be positive");
    }

    public String getId() { return id; }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }
    public List<String> getStatement() { return statement; }
}
