// 2.7 for loop: repeat a known number of times
// Run: java ForLoop.java
public class ForLoop {
    public static void main(String[] args) {
        // for (start; condition; step)
        for (int lap = 1; lap <= 5; lap++) {
            System.out.println("Running lap " + lap);
        }

        // Counting down
        for (int t = 3; t >= 1; t--) {
            System.out.println("Launch in " + t + "...");
        }
        System.out.println("Liftoff!");

        // Stepping by more than one
        System.out.print("Even numbers to 10: ");
        for (int n = 0; n <= 10; n += 2) {
            System.out.print(n + " ");
        }
        System.out.println();

        // Accumulating a total: a 12-month savings plan
        double monthlyDeposit = 200;
        double total = 0;
        for (int month = 1; month <= 12; month++) {
            total += monthlyDeposit;
        }
        System.out.println("Saved in a year: $" + total);

        // The for-each loop walks through every item of an array
        String[] playlist = {"Intro", "Sunrise", "Highway", "Outro"};
        for (String song : playlist) {
            System.out.println("Now playing: " + song);
        }
    }
}
