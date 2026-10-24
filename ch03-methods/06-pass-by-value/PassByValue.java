// 3.6 Pass-by-value: what a method receives is a copy
// Run: java PassByValue.java
import java.util.Arrays;

public class PassByValue {

    static void tryToDouble(int number) {
        number = number * 2;                 // changes only the local copy
        System.out.println("  inside tryToDouble: " + number);
    }

    static void addBonusToAll(int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] += 5;                  // changes the SHARED array the copy points to
        }
    }

    static void replaceArray(int[] scores) {
        scores = new int[]{0, 0, 0};         // only the local copy now points elsewhere
    }

    static int doubled(int number) {
        return number * 2;                   // the right way: return the new value
    }

    public static void main(String[] args) {
        int points = 10;
        tryToDouble(points);
        System.out.println("after tryToDouble: " + points);      // still 10

        points = doubled(points);
        System.out.println("after points = doubled(points): " + points);

        // Arrays (and objects) are passed as a copy of the REFERENCE (address)
        int[] classScores = {70, 80, 90};
        addBonusToAll(classScores);
        System.out.println("after addBonusToAll: " + Arrays.toString(classScores));

        replaceArray(classScores);
        System.out.println("after replaceArray: " + Arrays.toString(classScores));
    }
}
