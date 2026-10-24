package com.example.tips;

public class TipCalculator {
    public static double tip(double bill, double percent) {
        if (bill < 0 || percent < 0) {
            throw new IllegalArgumentException("Bill and percent must not be negative");
        }
        return Math.round(bill * percent) / 100.0;
    }
}
