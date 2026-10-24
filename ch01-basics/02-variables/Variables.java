// 1.2 Variables: labelled boxes that hold values
// Run: java Variables.java
public class Variables {
    public static void main(String[] args) {
        // type  name       = value;
        String customerName = "Priya";
        int    cupsOrdered  = 2;
        double pricePerCup  = 3.50;

        System.out.println("Customer: " + customerName);
        System.out.println("Cups: " + cupsOrdered);

        // A variable can be given a new value later (the box keeps its label)
        cupsOrdered = cupsOrdered + 1;    // Priya adds one more cup
        System.out.println("Cups after update: " + cupsOrdered);

        // Use variables to compute new values
        double total = cupsOrdered * pricePerCup;
        System.out.println("Total to pay: $" + total);

        // Declare first, assign later
        int loyaltyPoints;
        loyaltyPoints = cupsOrdered * 10;
        System.out.println("Loyalty points earned: " + loyaltyPoints);

        // 'var' lets the compiler infer the type from the value (Java 10+)
        var cafe = "Bean There";          // inferred as String
        System.out.println("Cafe: " + cafe);
    }
}
