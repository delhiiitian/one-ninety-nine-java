// 7.1 Packages and imports
// Run: bash run.sh
package com.cafe.app;

import com.cafe.billing.Bill;
import com.cafe.menu.MenuItem;
import static java.lang.Math.round;   // static import: use round(...) without "Math."

public class Main {
    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.add(new MenuItem("Flat white", 3.40));
        bill.add(new MenuItem("Croissant", 2.75));

        // A fully qualified name works without any import
        java.time.DayOfWeek today = java.time.DayOfWeek.FRIDAY;

        System.out.println(today + " bill: " + bill.count() + " items, total $" + round(bill.total() * 100) / 100.0);
        System.out.println("Main lives in package: " + Main.class.getPackageName());
        System.out.println("MenuItem lives in package: " + MenuItem.class.getPackageName());
    }
}
