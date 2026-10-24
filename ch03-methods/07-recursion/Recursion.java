// 3.7 Recursion: a method that calls itself
// Run: java Recursion.java
public class Recursion {

    // n! = n * (n-1) * ... * 1
    static long factorial(int n) {
        if (n <= 1) {            // BASE CASE: stops the recursion
            return 1;
        }
        return n * factorial(n - 1);   // RECURSIVE CASE: a smaller version of the same problem
    }

    // Russian dolls: open one, and if there is another inside, open that too
    static void openDoll(int size) {
        if (size == 0) {
            System.out.println("  Found the tiny solid doll!");
            return;
        }
        System.out.println("  Opening doll of size " + size);
        openDoll(size - 1);
    }

    // Sum of digits: 1234 -> 1 + 2 + 3 + 4
    static int sumDigits(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + sumDigits(n / 10);
    }

    // Fibonacci: each number is the sum of the two before it
    static int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));
        System.out.println("20! = " + factorial(20));
        openDoll(3);
        System.out.println("sumDigits(1234) = " + sumDigits(1234));
        System.out.print("First 10 Fibonacci numbers: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
        // Without a base case, recursion never stops and ends in StackOverflowError.
    }
}
