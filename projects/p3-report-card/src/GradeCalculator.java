// Pure helper methods: they take values in and hand results back (Chapter 3.1 - 3.4).
public class GradeCalculator {

    public static final int PASS_MARK = 40;

    // Overloaded: average of an int array...
    public static double average(int[] values) {
        if (values.length == 0) return 0;
        int sum = 0;
        for (int v : values) sum += v;
        return (double) sum / values.length;
    }

    // ...or of a double array (used for the class average)
    public static double average(double[] values) {
        if (values.length == 0) return 0;
        double sum = 0;
        for (double v : values) sum += v;
        return sum / values.length;
    }

    public static String letterGrade(double average) {
        if (average >= 90) return "A+";
        if (average >= 80) return "A";
        if (average >= 70) return "B";
        if (average >= 60) return "C";
        if (average >= 50) return "D";
        return "F";
    }

    public static boolean isPass(int mark) {
        return mark >= PASS_MARK;
    }

    // Recursion (Chapter 3.7): highest value in an array, checking from index i onwards
    public static int highest(int[] values, int i) {
        if (i == values.length - 1) return values[i];          // base case: last element
        return Math.max(values[i], highest(values, i + 1));    // recursive case
    }

    // A text bar for quick visual comparison: 87 -> "########."
    public static String bar(double value) {
        int full = (int) (value / 10);
        return "#".repeat(full) + (value % 10 >= 5 ? "." : "");
    }
}
