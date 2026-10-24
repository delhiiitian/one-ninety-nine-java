// 8.7 Generics: one container, any type, still type-safe
// Run: java Generics.java
import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        // A labelled storage box that can hold any ONE type
        Box<String> letterBox = new Box<>("Birthday card");
        Box<Integer> coinBox = new Box<>(25);
        System.out.println("Letter box holds: " + letterBox.get());
        System.out.println("Coin box holds: " + (coinBox.get() + 75));   // no cast needed: it is an Integer
        // letterBox.put(42);   // compile error: this box only takes Strings

        Pair<String, Double> price = new Pair<>("Laptop", 899.0);
        System.out.println(price.first() + " costs $" + price.second());

        // Generic methods
        List<Integer> scores = List.of(71, 94, 88);
        List<String> names = List.of("Zoe", "Adam", "Mila");
        System.out.println("Highest score: " + largest(scores));
        System.out.println("Last name alphabetically: " + largest(names));
        System.out.println("First of names: " + firstOrDefault(new ArrayList<String>(), "nobody"));

        // Bounded wildcard: accepts List<Integer>, List<Double>, any List of Numbers
        System.out.println("Sum: " + sum(List.of(1.5, 2.5)) + " and " + sum(scores));
    }

    // <T extends Comparable<T>>: T must be something that can be compared
    static <T extends Comparable<T>> T largest(List<T> items) {
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) best = item;
        }
        return best;
    }

    static <T> T firstOrDefault(List<T> items, T fallback) {
        return items.isEmpty() ? fallback : items.get(0);
    }

    static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number n : numbers) total += n.doubleValue();
        return total;
    }
}

class Box<T> {          // T is a placeholder filled in by whoever creates a Box
    private T item;

    Box(T item) { this.item = item; }

    T get() { return item; }

    void put(T item) { this.item = item; }
}

record Pair<A, B>(A first, B second) { }
