// 4.1 Arrays: a fixed row of numbered boxes
// Run: java Arrays101.java
import java.util.Arrays;

public class Arrays101 {
    public static void main(String[] args) {
        // Create with values
        double[] dailyTemps = {21.5, 23.0, 19.8, 25.1, 22.4, 18.9, 20.0};

        // Create with a size: every slot starts at the default (0, false, null)
        String[] parkingSpots = new String[5];
        parkingSpots[0] = "KA-01-1234";
        parkingSpots[3] = "MH-12-9876";

        // Access by index: the first index is 0, the last is length - 1
        System.out.println("Monday: " + dailyTemps[0]);
        System.out.println("Sunday: " + dailyTemps[dailyTemps.length - 1]);
        System.out.println("Spot 3 holds: " + parkingSpots[3]);
        System.out.println("Spot 1 holds: " + parkingSpots[1]);   // null: empty

        // Update a slot
        dailyTemps[2] = 20.3;

        // Loop through to compute stats
        double sum = 0, max = dailyTemps[0];
        for (double t : dailyTemps) {
            sum += t;
            if (t > max) max = t;
        }
        System.out.printf("Average: %.2f, Hottest: %.1f%n", sum / dailyTemps.length, max);

        // Helpers in java.util.Arrays
        System.out.println("All temps: " + Arrays.toString(dailyTemps));
        double[] sorted = dailyTemps.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted:    " + Arrays.toString(sorted));
        int[] filled = new int[4];
        Arrays.fill(filled, 7);
        System.out.println("Filled:    " + Arrays.toString(filled));

        // The size is FIXED. dailyTemps[7] would throw ArrayIndexOutOfBoundsException.
        System.out.println("Length is fixed at " + dailyTemps.length);
    }
}
