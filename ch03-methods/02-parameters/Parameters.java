// 3.2 Parameters and arguments: giving a method information
// Run: java Parameters.java
public class Parameters {

    // name and age are PARAMETERS: placeholders filled in at call time
    static void greet(String name, int age) {
        System.out.println("Hello " + name + ", you are " + age + " years old.");
    }

    static void printReceiptLine(String item, int quantity, double unitPrice) {
        double lineTotal = quantity * unitPrice;
        System.out.printf("%-12s x%d  $%6.2f%n", item, quantity, lineTotal);
    }

    static void repeat(String text, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(text);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // "Leila" and 30 are ARGUMENTS: the actual values passed in
        greet("Leila", 30);
        greet("Tom", 8);

        printReceiptLine("Sandwich", 2, 4.50);
        printReceiptLine("Juice", 3, 2.25);

        // Order and types must match the parameter list
        repeat("-=", 10);
        // repeat(10, "-=");   // compile error: arguments in the wrong order

        // Variables work as arguments too
        String guest = "Mina";
        int guestAge = 41;
        greet(guest, guestAge);
    }
}
