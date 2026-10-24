// 7.6 Unit testing: code that checks your code
// Run: java UnitTesting.java
// This file builds a tiny test runner so you can see the idea with no downloads.
// Real projects use JUnit (see 05-maven-build/src/test for the same idea in JUnit 5).
public class UnitTesting {

    // ---- The code under test -------------------------------------------
    static class PasswordChecker {
        static boolean isStrong(String pw) {
            if (pw == null || pw.length() < 8) return false;
            boolean hasDigit = false, hasUpper = false;
            for (char c : pw.toCharArray()) {
                if (Character.isDigit(c)) hasDigit = true;
                if (Character.isUpperCase(c)) hasUpper = true;
            }
            return hasDigit && hasUpper;
        }
    }

    // ---- A mini test framework -----------------------------------------
    static int passed = 0, failed = 0;

    static void check(String testName, boolean expected, boolean actual) {
        if (expected == actual) {
            passed++;
            System.out.println("PASS  " + testName);
        } else {
            failed++;
            System.out.println("FAIL  " + testName + " (expected " + expected + ", got " + actual + ")");
        }
    }

    // ---- The tests: one small behaviour each ---------------------------
    public static void main(String[] args) {
        check("short password is weak",        false, PasswordChecker.isStrong("Ab1"));
        check("no digit is weak",               false, PasswordChecker.isStrong("Password"));
        check("no uppercase is weak",           false, PasswordChecker.isStrong("password1"));
        check("upper + digit + 8 chars strong", true,  PasswordChecker.isStrong("Sunshine7"));
        check("null is weak, not a crash",      false, PasswordChecker.isStrong(null));

        System.out.println(passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);   // a non-zero exit code makes build tools stop
    }
}
