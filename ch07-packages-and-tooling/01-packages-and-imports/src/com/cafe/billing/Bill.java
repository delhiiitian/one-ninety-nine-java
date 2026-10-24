package com.cafe.billing;

import com.cafe.menu.MenuItem;    // use a class from another package
import java.util.ArrayList;       // and one from the JDK
import java.util.List;

public class Bill {
    private final List<MenuItem> items = new ArrayList<>();

    public void add(MenuItem item) { items.add(item); }

    public double total() {
        double sum = 0;
        for (MenuItem item : items) sum += item.getPrice();
        return sum;
    }

    public int count() { return items.size(); }
}
