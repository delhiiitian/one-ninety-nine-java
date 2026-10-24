// Project 1: Restaurant Bill Splitter
// Uses only Chapter 1 ideas: variables, types, strings, operators, casting, Scanner, constants, printf.
// Run: bash run.sh   (or: java src/BillSplitter.java)
import java.util.Scanner;

public class BillSplitter {

    // ---- Settings that rarely change live in constants ----
    static final double TAX_RATE = 0.05;          // 5% sales tax
    static final double SERVICE_FEE = 1.50;       // flat fee per bill
    static final String RESTAURANT = "The Hungry Coder";
    static final int LINE_WIDTH = 34;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // ---- 1. Collect the order ----
        System.out.println("Welcome to " + RESTAURANT + " bill splitter!");
        System.out.print("Table number: ");
        int table = in.nextInt();
        in.nextLine();                              // eat the Enter key

        System.out.print("Starter name: ");
        String starterName = in.nextLine().strip();
        System.out.print("Starter price: ");
        double starterPrice = in.nextDouble();
        in.nextLine();

        System.out.print("Main course name: ");
        String mainName = in.nextLine().strip();
        System.out.print("Main course price: ");
        double mainPrice = in.nextDouble();
        in.nextLine();

        System.out.print("Dessert name: ");
        String dessertName = in.nextLine().strip();
        System.out.print("Dessert price: ");
        double dessertPrice = in.nextDouble();

        System.out.print("Tip percent (e.g. 10): ");
        int tipPercent = in.nextInt();
        System.out.print("Number of people: ");
        int people = in.nextInt();
        in.close();

        // ---- 2. Do the maths ----
        double subtotal = starterPrice + mainPrice + dessertPrice;
        double tax = subtotal * TAX_RATE;
        double tip = subtotal * tipPercent / 100.0;          // 100.0 avoids integer division
        double total = subtotal + tax + tip + SERVICE_FEE;

        // Round each share UP to the next cent so the restaurant is never short.
        long totalCents = Math.round(total * 100);
        long shareCents = (totalCents + people - 1) / people;   // integer "ceiling division"
        double share = shareCents / 100.0;
        double extra = (shareCents * people - totalCents) / 100.0;   // work in cents to avoid -0.00

        // ---- 3. Print the receipt ----
        String rule = "=".repeat(LINE_WIDTH);
        System.out.println();
        System.out.println(rule);
        System.out.printf("%s%n", centre(RESTAURANT.toUpperCase(), LINE_WIDTH));
        System.out.printf("%s%n", centre("Table " + table, LINE_WIDTH));
        System.out.println(rule);
        System.out.printf("%-24s %9.2f%n", starterName, starterPrice);
        System.out.printf("%-24s %9.2f%n", mainName, mainPrice);
        System.out.printf("%-24s %9.2f%n", dessertName, dessertPrice);
        System.out.println("-".repeat(LINE_WIDTH));
        System.out.printf("%-24s %9.2f%n", "Subtotal", subtotal);
        System.out.printf("%-24s %9.2f%n", "Tax (" + (int) (TAX_RATE * 100) + "%)", tax);
        System.out.printf("%-24s %9.2f%n", "Tip (" + tipPercent + "%)", tip);
        System.out.printf("%-24s %9.2f%n", "Service fee", SERVICE_FEE);
        System.out.println(rule);
        System.out.printf("%-24s %9.2f%n", "TOTAL", totalCents / 100.0);
        System.out.printf("%-24s %9.2f%n", "Each of " + people + " pays", share);
        System.out.printf("%-24s %9.2f%n", "Extra tip from rounding", extra);
        System.out.println(rule);

        // A friendly closing line built from string methods
        String firstWord = mainName.split(" ")[0];
        System.out.println("Hope you enjoyed the " + firstWord.toLowerCase() + "!");
    }

    // Pads text with spaces on both sides so it sits in the middle of the line.
    static String centre(String text, int width) {
        int left = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, left)) + text;
    }
}
