// Project 5: Bank Account System
// An ATM-style console app where every kind of mistake is caught and explained, never a crash.
// Run: bash run.sh   (or a scripted session: bash run.sh --sample)
import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.open("Nadia", "1234", 500);       // AC1001
        bank.open("Victor", "4321", 120);      // AC1002

        Scanner in = new Scanner(System.in);
        System.out.println("Java 199 Bank ATM. Commands: balance ID PIN | deposit ID AMT | withdraw ID PIN AMT | transfer FROM PIN TO AMT | statement ID | quit");

        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.equals("quit")) break;
            System.out.println("> " + line);
            String[] p = line.split("\\s+");
            try {
                handle(bank, p);
            } catch (InsufficientFundsException e) {
                System.out.printf("  DECLINED: %s. Try $%.2f less.%n", e.getMessage(), e.getShortBy());
            } catch (BankException e) {                       // any other banking rule
                System.out.println("  DECLINED: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("  ERROR: amounts must be numbers");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("  ERROR: missing details for '" + p[0] + "'");
            } catch (IllegalArgumentException e) {
                System.out.println("  ERROR: " + e.getMessage());
            } finally {
                // Runs after every command, success or failure, like an ATM printing a slip
                System.out.println("  ---");
            }
        }
        System.out.println("Session ended. Total money in bank: " + String.format("$%.2f", totalDeposits(bank)));
    }

    static void handle(Bank bank, String[] p) throws BankException {
        switch (p[0]) {
            case "balance" -> {
                Account a = bank.find(p[1]);
                a.verifyPin(p[2]);
                System.out.printf("  %s, your balance is $%.2f%n", a.getOwner(), a.getBalance());
            }
            case "deposit" -> {
                Account a = bank.find(p[1]);
                a.deposit(Double.parseDouble(p[2]));
                System.out.printf("  Deposited. New balance $%.2f%n", a.getBalance());
            }
            case "withdraw" -> {
                Account a = bank.find(p[1]);
                a.verifyPin(p[2]);
                a.withdraw(Double.parseDouble(p[3]));
                System.out.printf("  Please take your cash. New balance $%.2f%n", a.getBalance());
            }
            case "transfer" -> {
                bank.find(p[1]).verifyPin(p[2]);
                bank.transfer(p[1], p[3], Double.parseDouble(p[4]));
                System.out.println("  Transfer complete.");
            }
            case "statement" -> bank.find(p[1]).getStatement().forEach(s -> System.out.println("  " + s));
            default -> throw new IllegalArgumentException("unknown command " + p[0]);
        }
    }

    static double totalDeposits(Bank bank) {
        double total = 0;
        for (Account a : bank.all().values()) total += a.getBalance();
        return total;
    }
}
