// 2.9 Nested loops: a loop inside a loop
// Run: java NestedLoops.java
public class NestedLoops {
    public static void main(String[] args) {
        // Multiplication table: rows x columns
        System.out.println("Multiplication table 1-5:");
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 5; col++) {
                System.out.printf("%4d", row * col);
            }
            System.out.println();      // end of row
        }

        // Drawing a staircase pattern
        System.out.println("Staircase:");
        for (int step = 1; step <= 4; step++) {
            for (int block = 1; block <= step; block++) {
                System.out.print("#");
            }
            System.out.println();
        }

        // A clock: hours and minutes (only every 20 minutes to keep output short)
        System.out.println("Bus timetable:");
        for (int hour = 8; hour <= 9; hour++) {
            for (int minute = 0; minute < 60; minute += 20) {
                System.out.printf("%02d:%02d  ", hour, minute);
            }
            System.out.println();
        }
    }
}
