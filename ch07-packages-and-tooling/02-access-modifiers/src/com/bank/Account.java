package com.bank;

public class Account {
    public final String owner;          // public: anyone can see it
    protected double interestRate;      // protected: same package + subclasses
    String branchCode = "BR-07";        // (no keyword) package-private: same package only
    private double balance;             // private: only code inside this class

    public Account(String owner, double opening) {
        this.owner = owner;
        this.balance = opening;
        this.interestRate = 0.02;
    }

    // The only doors into the private balance: controlled methods
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    private String secretPin() {         // helper hidden from the outside world
        return "4821";
    }

    public boolean checkPin(String pin) {
        return secretPin().equals(pin);
    }
}
