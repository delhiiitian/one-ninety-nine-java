// 2.6 do-while loop: run at least once, then check
// Run: java DoWhileLoop.java   (or: java DoWhileLoop.java < input.txt)
import java.util.Scanner;

public class DoWhileLoop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice;

        // The menu must be shown at least once, so do-while is a natural fit
        do {
            System.out.println("=== Coffee Machine ===");
            System.out.println("1. Espresso");
            System.out.println("2. Cappuccino");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = in.nextInt();

            if (choice == 1) {
                System.out.println("Brewing espresso...");
            } else if (choice == 2) {
                System.out.println("Frothing milk for cappuccino...");
            } else if (choice != 0) {
                System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);           // checked AFTER each round

        System.out.println("Goodbye!");
        in.close();
    }
}
