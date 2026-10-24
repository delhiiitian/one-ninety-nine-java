// 4.7 Iterating over collections: four ways to walk a list
// Run: java IteratingCollections.java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IteratingCollections {
    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>(List.of("email boss", "buy milk", "pay rent", "buy stamps"));

        // 1. Classic for with an index: when you need the position
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }

        // 2. Enhanced for (for-each): the simplest read-only walk
        for (String task : tasks) {
            System.out.println("- " + task);
        }

        // 3. Iterator: the safe way to REMOVE while walking
        Iterator<String> it = tasks.iterator();
        while (it.hasNext()) {
            String task = it.next();
            if (task.startsWith("buy")) {
                it.remove();               // a for-each loop here would throw ConcurrentModificationException
            }
        }
        System.out.println("After errands done: " + tasks);

        // 4. forEach with a lambda (more in Chapter 8)
        tasks.forEach(task -> System.out.println("TODO: " + task.toUpperCase()));

        // Maps: walk keys, values, or both
        Map<String, Integer> stock = new TreeMap<>(Map.of("apples", 12, "pears", 0, "plums", 5));
        for (String fruit : stock.keySet()) {
            System.out.print(fruit + " ");
        }
        System.out.println();
        stock.forEach((fruit, qty) -> {
            if (qty == 0) System.out.println("Reorder " + fruit + "!");
        });
    }
}
