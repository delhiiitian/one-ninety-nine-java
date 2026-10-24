// 7.2 Access modifiers: who may touch what
// Run: bash run.sh
package app;

import com.bank.Account;
import com.bank.Auditor;

public class Main {
    public static void main(String[] args) {
        Account acc = new Account("Farah", 500);
        acc.deposit(120);

        System.out.println("Owner (public field): " + acc.owner);
        System.out.println("Balance via public getter: " + acc.getBalance());
        System.out.println("PIN 0000 correct? " + acc.checkPin("0000"));
        System.out.println("Auditor sees: " + Auditor.inspect(acc));

        // None of these compile from package 'app':
        // acc.balance = 1_000_000;   // private
        // acc.branchCode;            // package-private
        // acc.interestRate;          // protected (Main is not a subclass)
        try {
            acc.deposit(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Blocked: " + e.getMessage());
        }
    }
}
