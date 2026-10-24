// 2.2 if and else: making a decision
// Run: java IfElse.java
public class IfElse {
    public static void main(String[] args) {
        double balance = 40.00;
        double price = 55.00;

        if (balance >= price) {
            balance = balance - price;
            System.out.println("Payment approved. New balance: " + balance);
        } else {
            double shortBy = price - balance;
            System.out.println("Payment declined. You are short by $" + shortBy);
        }

        // if without else: do something extra only when needed
        int batteryPercent = 15;
        if (batteryPercent < 20) {
            System.out.println("Low battery! Turning on power saver.");
        }
        System.out.println("Battery: " + batteryPercent + "%");

        // The ternary operator: a one-line if/else that produces a value
        int itemsInCart = 1;
        String word = (itemsInCart == 1) ? "item" : "items";
        System.out.println("You have " + itemsInCart + " " + word + " in your cart.");

        // Always use braces, even for one line. This looks right but is a bug:
        boolean isMember = false;
        if (isMember)
            System.out.println("Member discount applied.");
        System.out.println("(This line always runs: only the first line belongs to the if.)");
    }
}
