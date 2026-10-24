// 5.6 Throwing exceptions: refusing bad input
// Run: java ThrowingExceptions.java
public class ThrowingExceptions {

    static void setAlarm(int hour, int minute) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour must be 0-23, got " + hour);
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute must be 0-59, got " + minute);
        }
        System.out.printf("Alarm set for %02d:%02d%n", hour, minute);
    }

    static double withdraw(double balance, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds: balance " + balance + ", requested " + amount);
        }
        return balance - amount;
    }

    public static void main(String[] args) {
        setAlarm(6, 30);
        try {
            setAlarm(25, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        double balance = 100;
        try {
            balance = withdraw(balance, 40);
            System.out.println("Balance now " + balance);
            balance = withdraw(balance, 500);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // Wrapping: keep the original cause when re-throwing as a clearer exception
        try {
            loadSettings("port=abc");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage() + " | cause: " + e.getCause().getClass().getSimpleName());
        }
    }

    static int loadSettings(String line) {
        try {
            return Integer.parseInt(line.split("=")[1]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid settings line '" + line + "'", e);
        }
    }
}
