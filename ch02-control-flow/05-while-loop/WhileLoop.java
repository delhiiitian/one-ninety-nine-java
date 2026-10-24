// 2.5 while loop: repeat as long as a condition is true
// Run: java WhileLoop.java
public class WhileLoop {
    public static void main(String[] args) {
        // Saving up for a bike: how many weeks?
        double savings = 0;
        double weeklyAllowance = 15;
        double bikePrice = 100;
        int weeks = 0;

        while (savings < bikePrice) {        // checked BEFORE each round
            savings += weeklyAllowance;
            weeks++;
            System.out.println("Week " + weeks + ": saved $" + savings);
        }
        System.out.println("Bike bought after " + weeks + " weeks!");

        // A loop may run zero times if the condition starts false
        int ticketsLeft = 0;
        while (ticketsLeft > 0) {
            System.out.println("Selling a ticket");   // never printed
            ticketsLeft--;
        }

        // Counting digits of a number
        int number = 907_215;
        int digits = 0;
        while (number > 0) {
            number = number / 10;            // chop off the last digit
            digits++;
        }
        System.out.println("907215 has " + digits + " digits");
        // Warning: forgetting to change the condition variable creates an infinite loop.
    }
}
