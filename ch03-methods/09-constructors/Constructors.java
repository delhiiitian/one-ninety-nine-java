// 3.9 Constructors and the 'this' keyword
// Run: java Constructors.java

public class Constructors {
    public static void main(String[] args) {
        BankAccount a = new BankAccount("Rosa", "ACC-1001", 250.00);
        BankAccount b = new BankAccount("Omar", "ACC-1002");    // uses the 2-argument constructor

        b.deposit(75.50);

        System.out.println(a.summary());
        System.out.println(b.summary());
        // Without constructors, every object would start half-filled and need many setup lines.
    }
}

class BankAccount {
    String owner;
    String accountNumber;
    double balance;

    // A constructor runs once, when the object is created with 'new'.
    // It has the class name and no return type.
    BankAccount(String owner, String accountNumber, double openingDeposit) {
        this.owner = owner;                 // this.owner = the field, owner = the parameter
        this.accountNumber = accountNumber;
        this.balance = openingDeposit;
    }

    // Overloaded constructor: open an account with zero balance
    BankAccount(String owner, String accountNumber) {
        this(owner, accountNumber, 0.0);    // reuse the main constructor
    }

    void deposit(double amount) {
        this.balance += amount;
    }

    String summary() {
        return String.format("%s [%s] balance $%.2f", owner, accountNumber, balance);
    }
}
