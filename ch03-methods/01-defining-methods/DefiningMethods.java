// 3.1 Defining and calling methods
// Run: java DefiningMethods.java
public class DefiningMethods {

    // A method is a named, reusable block of code.
    // static: belongs to the class (no object needed); void: returns nothing
    static void printBanner() {
        System.out.println("*****************************");
        System.out.println("*  Welcome to Pizza Palace  *");
        System.out.println("*****************************");
    }

    static void printMenu() {
        System.out.println("1. Margherita  $8");
        System.out.println("2. Pepperoni   $10");
        System.out.println("3. Veggie      $9");
    }

    static void printFooter() {
        System.out.println("Thank you for ordering!");
    }

    public static void main(String[] args) {
        // Calling (invoking) methods: the program jumps in, runs, and comes back
        printBanner();
        printMenu();
        printFooter();

        System.out.println();
        System.out.println("Second customer arrives...");
        printBanner();     // reuse without copy-pasting
        printMenu();
    }
}
