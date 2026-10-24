// 1.5 Operators: doing math and updating values
// Run: java Operators.java
public class Operators {
    public static void main(String[] args) {
        int pizzas = 7;
        int friends = 3;

        System.out.println("Addition:       " + (pizzas + friends));
        System.out.println("Subtraction:    " + (pizzas - friends));
        System.out.println("Multiplication: " + (pizzas * friends));
        System.out.println("Division (int): " + (pizzas / friends));   // 2: decimals are dropped
        System.out.println("Remainder:      " + (pizzas % friends));   // 1 pizza left over
        System.out.println("Division (dbl): " + (pizzas / (double) friends));

        // Order of operations: * and / before + and -
        System.out.println("2 + 3 * 4 = " + (2 + 3 * 4));
        System.out.println("(2 + 3) * 4 = " + ((2 + 3) * 4));

        // Compound assignment
        int score = 10;
        score += 5;   // score = score + 5
        score -= 2;   // score = score - 2
        score *= 3;   // score = score * 3
        score /= 3;   // score = score / 3
        System.out.println("Score: " + score);

        // Increment and decrement
        int visitors = 0;
        visitors++;
        visitors++;
        visitors--;
        System.out.println("Visitors: " + visitors);

        // Prefix vs postfix
        int x = 5;
        int a = x++;   // a gets 5, then x becomes 6
        int b = ++x;   // x becomes 7, then b gets 7
        System.out.println("a=" + a + " b=" + b + " x=" + x);

        // Handy math helpers
        System.out.println("Math.max(4, 9) = " + Math.max(4, 9));
        System.out.println("Math.pow(2, 10) = " + Math.pow(2, 10));
        System.out.println("Math.sqrt(81) = " + Math.sqrt(81));
        System.out.println("Math.round(4.6) = " + Math.round(4.6));
    }
}
