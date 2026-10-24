package com.bank;

// Same package as Account, so it can read package-private and protected members
public class Auditor {
    public static String inspect(Account a) {
        return a.owner + " @ " + a.branchCode + ", rate " + a.interestRate;
        // a.balance would NOT compile here: it is private to Account
    }
}
