// 1.6 Type conversion and casting
// Run: java TypeCasting.java
public class TypeCasting {
    public static void main(String[] args) {
        // Widening: small box into big box, automatic and safe
        int steps = 9500;
        long totalSteps = steps;          // int -> long
        double stepsAsDecimal = steps;    // int -> double
        System.out.println("Widened: " + totalSteps + ", " + stepsAsDecimal);

        // Narrowing: big box into small box, you must cast explicitly
        double price = 19.99;
        int wholeDollars = (int) price;   // decimal part is cut off, not rounded
        System.out.println("(int) 19.99 = " + wholeDollars);
        System.out.println("Math.round(19.99) = " + Math.round(price));

        // Narrowing can lose data
        int big = 300;
        byte small = (byte) big;          // byte max is 127, so it wraps
        System.out.println("(byte) 300 = " + small);

        // The integer division trap and how casting fixes it
        int correct = 7, total = 9;
        System.out.println("Wrong %: " + (correct / total * 100));
        System.out.println("Right %: " + ((double) correct / total * 100));

        // char and int are related: each char has a number code
        char letter = 'A';
        int code = letter;
        char next = (char) (code + 1);
        System.out.println("'A' code = " + code + ", next letter = " + next);

        // Converting between String and numbers
        String ageText = "42";
        int age = Integer.parseInt(ageText);
        double rate = Double.parseDouble("3.75");
        String back = String.valueOf(age + 1);
        System.out.println("Parsed: " + age + ", " + rate + ", back to text: " + back);
    }
}
