// Base class for every banking problem. Checked: callers must decide what to do (Chapter 5.5, 5.7).
public class BankException extends Exception {
    public BankException(String message) {
        super(message);
    }
}
