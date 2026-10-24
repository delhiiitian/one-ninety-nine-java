// Project 2: Number Guessing Game
// Uses Chapter 2 ideas: booleans, if/else-if, switch, while, do-while, for, break/continue, nested loops.
// Run: bash run.sh            Repeatable game for testing: java src/GuessingGame.java 42  (42 = random seed)
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // A seed makes the "random" numbers repeatable, which lets us test the game automatically.
        Random random = args.length > 0 ? new Random(Long.parseLong(args[0])) : new Random();

        int gamesPlayed = 0;
        int gamesWon = 0;
        int bestScore = Integer.MAX_VALUE;     // fewer guesses = better
        String again;

        System.out.println("=== GUESS THE NUMBER ===");

        do {                                   // outer loop: one round per pass
            // ---- 1. Choose difficulty ----
            System.out.println();
            System.out.println("Difficulty: 1) Easy 1-20, 8 tries  2) Medium 1-50, 7 tries  3) Hard 1-100, 6 tries");
            System.out.print("Pick 1-3: ");
            int level = in.nextInt();

            int max;
            int maxTries;
            switch (level) {
                case 1 -> { max = 20; maxTries = 8; }
                case 2 -> { max = 50; maxTries = 7; }
                case 3 -> { max = 100; maxTries = 6; }
                default -> {
                    System.out.println("Unknown level, using Medium.");
                    max = 50;
                    maxTries = 7;
                }
            }

            int secret = random.nextInt(max) + 1;   // 1..max
            boolean won = false;
            int tries = 0;
            int low = 1, high = max;               // the range still possible, shown as a hint

            // ---- 2. Guessing loop ----
            while (tries < maxTries) {
                System.out.print("Guess (" + low + "-" + high + "), " + (maxTries - tries) + " tries left: ");
                int guess = in.nextInt();

                if (guess < 1 || guess > max) {
                    System.out.println("  Out of range! That one is free.");
                    continue;                      // skip the rest; does not count as a try
                }
                tries++;

                if (guess == secret) {
                    won = true;
                    break;                         // leave the loop early
                } else if (guess < secret) {
                    System.out.println("  Too low." + warmth(guess, secret));
                    low = Math.max(low, guess + 1);
                } else {
                    System.out.println("  Too high." + warmth(guess, secret));
                    high = Math.min(high, guess - 1);
                }
            }

            // ---- 3. Round result ----
            gamesPlayed++;
            if (won) {
                gamesWon++;
                bestScore = Math.min(bestScore, tries);
                System.out.println("Correct! " + secret + " found in " + tries + " " + (tries == 1 ? "try" : "tries") + ".");
                printStars(maxTries - tries + 1);
            } else {
                System.out.println("Out of tries. The number was " + secret + ".");
            }

            System.out.print("Play again? (y/n): ");
            again = in.next();
        } while (again.equalsIgnoreCase("y"));

        // ---- 4. Final statistics ----
        System.out.println();
        System.out.println("Games played: " + gamesPlayed);
        System.out.println("Games won:    " + gamesWon);
        if (gamesWon > 0) {
            System.out.println("Best score:   " + bestScore + " tries");
        }
        System.out.printf("Win rate:     %.0f%%%n", gamesPlayed == 0 ? 0.0 : gamesWon * 100.0 / gamesPlayed);
        System.out.println("Thanks for playing!");
        in.close();
    }

    // A temperature hint: how close was the guess?
    static String warmth(int guess, int secret) {
        int distance = Math.abs(guess - secret);
        if (distance <= 2) return " Burning hot!";
        if (distance <= 5) return " Warm.";
        if (distance <= 15) return " Cool.";
        return " Freezing.";
    }

    // Rows of stars as a reward: a small nested loop
    static void printStars(int rows) {
        for (int r = 1; r <= Math.min(rows, 4); r++) {
            for (int s = 0; s < r; s++) System.out.print("* ");
            System.out.println();
        }
    }
}
