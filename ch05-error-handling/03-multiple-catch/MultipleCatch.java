// 5.3 Multiple catch blocks: different problems, different responses
// Run: java MultipleCatch.java
public class MultipleCatch {
    public static void main(String[] args) {
        String[][] testCases = {
                {"4", "2"},      // works
                {"4", "0"},      // divide by zero
                {"4", "x"},      // not a number
                {"4"}            // missing value
        };
        for (String[] tc : testCases) {
            splitBill(tc);
        }
    }

    static void splitBill(String[] values) {
        try {
            int bill = Integer.parseInt(values[0]);
            int people = Integer.parseInt(values[1]);
            System.out.println("Each pays " + bill / people);
        } catch (ArithmeticException e) {
            System.out.println("Cannot split between zero people.");
        } catch (NumberFormatException e) {
            System.out.println("Please type digits only: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Both the bill and the number of people are required.");
        }
        // Multi-catch with | when the response is the same:
        //   catch (NumberFormatException | ArithmeticException e) { ... }
        // Put specific types BEFORE general ones like Exception, or the compiler complains.
    }
}
