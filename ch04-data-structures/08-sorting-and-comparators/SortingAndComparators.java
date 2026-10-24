// 4.8 Sorting and searching with Comparator
// Run: java SortingAndComparators.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingAndComparators {
    public static void main(String[] args) {
        List<Integer> prices = new ArrayList<>(List.of(30, 5, 18, 99, 42));
        Collections.sort(prices);                                // natural order
        System.out.println("Low to high: " + prices);
        prices.sort(Comparator.reverseOrder());
        System.out.println("High to low: " + prices);

        List<Product> products = new ArrayList<>(List.of(
                new Product("Headphones", 59.99, 4.5),
                new Product("Charger", 19.99, 4.7),
                new Product("Speaker", 89.00, 4.5),
                new Product("Cable", 9.99, 3.9)));

        // Sort by price (cheapest first), like an online shop's "Price: low to high"
        products.sort(Comparator.comparingDouble(Product::price));
        print("By price:", products);

        // Sort by rating (best first), then by name for ties
        products.sort(Comparator.comparingDouble(Product::rating).reversed()
                .thenComparing(Product::name));
        print("By rating, then name:", products);

        // Binary search needs a SORTED list and finds items fast
        List<String> names = new ArrayList<>(List.of("Zara", "Amir", "Lena", "Kofi"));
        Collections.sort(names);
        System.out.println("Sorted names " + names + ", Lena at index " + Collections.binarySearch(names, "Lena"));

        System.out.println("Cheapest: " + Collections.min(products, Comparator.comparingDouble(Product::price)).name());
    }

    static void print(String title, List<Product> list) {
        System.out.println(title);
        for (Product p : list) {
            System.out.printf("  %-11s $%6.2f  %.1f stars%n", p.name(), p.price(), p.rating());
        }
    }
}

record Product(String name, double price, double rating) { }
