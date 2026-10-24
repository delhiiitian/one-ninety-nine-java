// 7.5 Maven build
// Run: mvn -q package && java -jar target/tip-calculator-1.0.0.jar
package com.example.tips;

public class TipApp {
    public static void main(String[] args) {
        double bill = 64.00;
        for (int percent : new int[]{10, 15, 20}) {
            System.out.printf("%d%% tip on $%.2f = $%.2f%n", percent, bill, TipCalculator.tip(bill, percent));
        }
    }
}
