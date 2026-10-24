// 3.5 Scope: where a variable can be seen
// Run: java Scope.java
public class Scope {

    // A class-level (static) variable: visible to every method in the class
    static int totalVisitors = 0;

    static void enterMuseum(String name) {
        int ticketNumber = totalVisitors + 1;   // local: exists only inside this method
        totalVisitors++;
        System.out.println(name + " gets ticket #" + ticketNumber);
    }

    public static void main(String[] args) {
        enterMuseum("Ana");
        enterMuseum("Ben");
        // System.out.println(ticketNumber);   // compile error: not visible here
        System.out.println("Total visitors: " + totalVisitors);

        // Block scope: a variable declared inside { } dies at the closing brace
        int hour = 14;
        if (hour > 12) {
            String period = "afternoon";
            System.out.println("Good " + period);
        }
        // System.out.println(period);          // compile error: period is out of scope

        // Loop variables live only inside the loop
        for (int i = 0; i < 3; i++) {
            int squared = i * i;
            System.out.println(i + " squared = " + squared);
        }
        // i and squared do not exist here

        // Shadowing: a local variable can hide a class variable with the same name
        int totalVisitors = 999;
        System.out.println("Local totalVisitors: " + totalVisitors);
        System.out.println("Class totalVisitors: " + Scope.totalVisitors);
    }
}
