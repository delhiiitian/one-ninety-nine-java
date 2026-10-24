// 3.3 Return values: getting an answer back
// Run: java ReturnValues.java
public class ReturnValues {

    // The return type (double) says what kind of answer comes back
    static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    static boolean isEligibleToVote(int age) {
        return age >= 18;
    }

    static double applyDiscount(double price, double percent) {
        if (percent <= 0) {
            return price;              // early return: nothing to do
        }
        return price - price * percent / 100;
    }

    static String initials(String first, String last) {
        return "" + first.charAt(0) + last.charAt(0);
    }

    public static void main(String[] args) {
        double f = celsiusToFahrenheit(25);
        System.out.println("25 C = " + f + " F");

        // Use the returned value directly in an expression or a condition
        System.out.println("100 C = " + celsiusToFahrenheit(100) + " F");
        if (isEligibleToVote(17)) {
            System.out.println("17 can vote");
        } else {
            System.out.println("17 cannot vote yet");
        }

        // Chain calls: the output of one feeds another
        double finalPrice = applyDiscount(applyDiscount(200, 10), 5);
        System.out.println("Price after 10% then 5% off: " + finalPrice);
        System.out.println("No discount: " + applyDiscount(50, 0));

        System.out.println("Initials of Grace Hopper: " + initials("Grace", "Hopper"));
    }
}
