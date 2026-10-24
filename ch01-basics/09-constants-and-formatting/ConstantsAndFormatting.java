// 1.9 Constants (final) and formatted output (printf)
// Run: java ConstantsAndFormatting.java
public class ConstantsAndFormatting {

    // A constant never changes once set
    static final double SALES_TAX_RATE = 0.08;
    static final String STORE_NAME = "Corner Grocery";

    public static void main(String[] args) {
        final int itemsInCart = 3;   // local constant
        // itemsInCart = 4;          // compile error: cannot assign a value to final variable

        double apples = 2.40, bread = 3.15, milk = 1.99;
        double subtotal = apples + bread + milk;
        double tax = subtotal * SALES_TAX_RATE;
        double total = subtotal + tax;

        System.out.println("Without formatting: " + total);   // ugly: many decimals

        // printf: %s text, %d whole number, %.2f decimal with 2 places, %n new line
        System.out.printf("%s%n", STORE_NAME);
        System.out.printf("%-10s %8.2f%n", "Apples", apples);   // - means left-align, 10 wide
        System.out.printf("%-10s %8.2f%n", "Bread", bread);
        System.out.printf("%-10s %8.2f%n", "Milk", milk);
        System.out.printf("%-10s %8.2f%n", "Tax", tax);
        System.out.printf("%-10s %8.2f%n", "TOTAL", total);
        System.out.printf("Items: %d, tax rate: %.0f%%%n", itemsInCart, SALES_TAX_RATE * 100);

        // String.format builds the text without printing it
        String label = String.format("Order #%05d", 42);   // pad with zeros to 5 digits
        System.out.println(label);
    }
}
