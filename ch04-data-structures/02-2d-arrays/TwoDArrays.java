// 4.2 Two-dimensional arrays: rows and columns
// Run: java TwoDArrays.java
public class TwoDArrays {
    public static void main(String[] args) {
        // A cinema with 3 rows of 4 seats: true = booked
        boolean[][] seats = new boolean[3][4];
        seats[0][1] = true;
        seats[1][1] = true;
        seats[1][2] = true;
        seats[2][3] = true;

        System.out.println("Cinema seating (X = booked):");
        for (int row = 0; row < seats.length; row++) {
            System.out.print("Row " + (char) ('A' + row) + ": ");
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print(seats[row][col] ? "[X]" : "[ ]");
            }
            System.out.println();
        }

        // A grade book: one row per student, one column per test
        int[][] grades = {
                {88, 92, 79},
                {65, 70, 81},
                {95, 98, 91}
        };
        String[] students = {"Ali", "Bea", "Cy"};
        for (int s = 0; s < grades.length; s++) {
            int total = 0;
            for (int g : grades[s]) {
                total += g;
            }
            System.out.printf("%-4s average %.1f%n", students[s], total / (double) grades[s].length);
        }

        // Rows can have different lengths (a "jagged" array)
        int[][] triangle = {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}};
        for (int[] row : triangle) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }
}
