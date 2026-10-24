// 7.4 JAR files: shipping your program as one file
// Run: bash run.sh
package com.dice;

import java.util.Random;

public class DiceRoller {
    public static void main(String[] args) {
        int dice = args.length > 0 ? Integer.parseInt(args[0]) : 2;
        Random random = new Random(7);          // fixed seed so the output is repeatable
        int total = 0;
        StringBuilder rolls = new StringBuilder();
        for (int i = 0; i < dice; i++) {
            int roll = random.nextInt(6) + 1;
            total += roll;
            rolls.append(roll).append(' ');
        }
        System.out.println("Rolled " + dice + " dice: " + rolls.toString().trim() + " (total " + total + ")");
    }
}
