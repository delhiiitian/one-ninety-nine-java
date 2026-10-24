// 8.8 Lambdas and functional interfaces: passing behaviour around
// Run: java Lambdas.java
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdas {
    public static void main(String[] args) {
        // A lambda is a short, unnamed method: (parameters) -> result
        Function<Double, Double> addVat = price -> price * 1.20;
        Predicate<String> isEmail = text -> text.contains("@");
        BiFunction<Integer, Integer, Integer> area = (w, h) -> w * h;
        Supplier<String> greeting = () -> "Good morning!";

        System.out.println("100 with VAT: " + addVat.apply(100.0));
        System.out.println("bob@mail.com is an email? " + isEmail.test("bob@mail.com"));
        System.out.println("Area 4x5: " + area.apply(4, 5));
        System.out.println(greeting.get());

        // Like giving a waiter instructions: the method decides WHEN, you decide WHAT
        List<String> files = new ArrayList<>(List.of("report.pdf", "cat.jpg", "budget.xlsx", "dog.jpg"));
        System.out.println("Images: " + filter(files, name -> name.endsWith(".jpg")));
        System.out.println("Long names: " + filter(files, name -> name.length() > 8));

        // Lambdas plug straight into JDK methods
        files.sort((a, b) -> a.length() - b.length());
        System.out.println("Sorted by length: " + files);
        files.removeIf(name -> name.startsWith("budget"));
        files.forEach(name -> System.out.println("  file: " + name));

        // Method references: a shortcut when the lambda only calls one existing method
        files.replaceAll(String::toUpperCase);
        files.forEach(System.out::println);

        // Combining small functions
        Function<Double, Double> discount = p -> p * 0.9;
        System.out.println("Discount then VAT on 50: " + discount.andThen(addVat).apply(50.0));
    }

    // Our own method that accepts behaviour as a parameter
    static List<String> filter(List<String> items, Predicate<String> rule) {
        List<String> result = new ArrayList<>();
        for (String item : items) {
            if (rule.test(item)) result.add(item);
        }
        return result;
    }
}
