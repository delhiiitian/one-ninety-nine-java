// 1.8 Comments and naming conventions
// Run: java CommentsAndNaming.java

/**
 * A Javadoc comment describes a class or method.
 * Tools turn these into web documentation (see Chapter 7).
 */
public class CommentsAndNaming {           // Classes: PascalCase

    static final int MAX_LOGIN_ATTEMPTS = 3; // Constants: UPPER_SNAKE_CASE

    public static void main(String[] args) {
        // Single-line comment: explains WHY, not just what
        int failedAttempts = 2;              // Variables: camelCase

        /*
         * Multi-line comment.
         * Useful for longer explanations or temporarily disabling code.
         */
        int attemptsLeft = MAX_LOGIN_ATTEMPTS - failedAttempts;

        boolean isLocked = attemptsLeft <= 0; // booleans read like questions: isX, hasX, canX
        printStatus(attemptsLeft, isLocked);

        // Bad names vs good names
        int d = 7;                 // what is d?
        int daysUntilExpiry = 7;   // instantly clear
        System.out.println("Bad name d=" + d + ", good name daysUntilExpiry=" + daysUntilExpiry);
    }

    /** Methods: camelCase verbs that say what they do. */
    static void printStatus(int attemptsLeft, boolean isLocked) {
        if (isLocked) {
            System.out.println("Account locked.");
        } else {
            System.out.println("Attempts left: " + attemptsLeft);
        }
    }
}
