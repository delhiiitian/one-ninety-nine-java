// 8.9 The Streams API: an assembly line for data
// Run: java Streams.java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams {

    record Sale(String product, String category, int quantity, double price) {
        double revenue() { return quantity * price; }
    }

    public static void main(String[] args) {
        List<Sale> sales = List.of(
                new Sale("Laptop", "Electronics", 3, 900),
                new Sale("Mouse", "Electronics", 25, 20),
                new Sale("Desk", "Furniture", 4, 250),
                new Sale("Chair", "Furniture", 10, 120),
                new Sale("Pen", "Stationery", 200, 1.5),
                new Sale("Notebook", "Stationery", 80, 4));

        // source -> filter -> map -> collect, like raw items moving through stations on a conveyor belt
        List<String> bigSellers = sales.stream()
                .filter(s -> s.quantity() >= 10)          // keep some
                .map(Sale::product)                       // transform each
                .sorted()
                .toList();                                // gather results
        System.out.println("Sold 10+ units: " + bigSellers);

        double totalRevenue = sales.stream().mapToDouble(Sale::revenue).sum();
        System.out.println("Total revenue: $" + totalRevenue);

        // Group by category and add up revenue: one line instead of a loop and a map
        Map<String, Double> byCategory = sales.stream()
                .collect(Collectors.groupingBy(Sale::category, java.util.TreeMap::new,
                        Collectors.summingDouble(Sale::revenue)));
        byCategory.forEach((cat, rev) -> System.out.printf("  %-11s $%,.2f%n", cat, rev));

        // Find the best product
        sales.stream()
                .max((a, b) -> Double.compare(a.revenue(), b.revenue()))
                .ifPresent(s -> System.out.println("Top earner: " + s.product()));

        // Quick questions
        System.out.println("Any item over $500? " + sales.stream().anyMatch(s -> s.price() > 500));
        System.out.println("Product names: " + sales.stream().map(Sale::product).collect(Collectors.joining(", ")));

        // Number streams
        System.out.println("Sum of 1..100: " + IntStream.rangeClosed(1, 100).sum());
        System.out.println("Squares of evens to 10: " + IntStream.rangeClosed(1, 10).filter(n -> n % 2 == 0).map(n -> n * n).boxed().toList());
    }
}
