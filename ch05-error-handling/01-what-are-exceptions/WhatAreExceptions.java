// 5.1 What is an exception? Reading a stack trace
// Run: java WhatAreExceptions.java
public class WhatAreExceptions {
    public static void main(String[] args) {
        System.out.println("Step 1: starting checkout");
        int[] cartQuantities = {2, 1, 3};

        // Each of these lines would crash the program if it ran unprotected.
        // We print the exception instead of letting it crash, so you can read it.
        try {
            System.out.println(cartQuantities[5]);           // index 5 does not exist
        } catch (Exception e) {
            System.out.println("Caught: " + e);
        }
        try {
            String coupon = null;
            System.out.println(coupon.length());              // calling a method on null
        } catch (Exception e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }
        try {
            int perPerson = 100 / 0;                          // division by zero
            System.out.println(perPerson);
        } catch (Exception e) {
            System.out.println("Caught: " + e);
        }
        try {
            int qty = Integer.parseInt("three");              // text that is not a number
            System.out.println(qty);
        } catch (Exception e) {
            System.out.println("Caught: " + e);
        }

        // A real stack trace shows the chain of method calls, newest first:
        try {
            placeOrder();
        } catch (Exception e) {
            System.out.println(e);
            for (StackTraceElement frame : e.getStackTrace()) {
                if (frame.getClassName().equals("WhatAreExceptions")) {   // hide the JVM's own frames
                    System.out.println("\tat " + frame);
                }
            }
            // e.printStackTrace() prints the full trace, including JVM internals
        }
        System.out.println("Step 2: program kept running because we handled the problems");
    }

    static void placeOrder() {
        chargeCard(-5);
    }

    static void chargeCard(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
        }
    }
}
