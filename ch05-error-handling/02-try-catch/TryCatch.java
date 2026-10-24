// 5.2 try and catch: handling a problem instead of crashing
// Run: java TryCatch.java
public class TryCatch {
    public static void main(String[] args) {
        String[] userInputs = {"25", "abc", "-4", "12"};

        for (String input : userInputs) {
            try {
                // Risky code goes inside try
                int age = Integer.parseInt(input);
                System.out.println("Valid age entered: " + age);
            } catch (NumberFormatException e) {
                // Runs ONLY if that kind of exception happens
                System.out.println("'" + input + "' is not a number. Please try again.");
            }
        }

        // The rest of the try block is skipped after the failing line
        try {
            System.out.println("Before the problem");
            int result = 10 / 0;
            System.out.println("This never prints " + result);
        } catch (ArithmeticException e) {
            System.out.println("Problem: " + e.getMessage());
        }

        // Turning a failure into a safe default
        int pageSize = parseOrDefault("fifty", 20);
        System.out.println("Page size: " + pageSize);
        System.out.println("Program continues normally.");
    }

    static int parseOrDefault(String text, int fallback) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
