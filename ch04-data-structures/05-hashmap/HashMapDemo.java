// 4.5 HashMap: look up a value by its key
// Run: java HashMapDemo.java
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HashMapDemo {
    public static void main(String[] args) {
        // Like a phone's contact list: name (key) -> number (value)
        Map<String, String> contacts = new HashMap<>();
        contacts.put("Mom", "555-0101");
        contacts.put("Dentist", "555-0199");
        contacts.put("Pizza", "555-0123");

        System.out.println("Mom's number: " + contacts.get("Mom"));
        System.out.println("Unknown: " + contacts.get("Plumber"));       // null when missing
        System.out.println("Plumber or default: " + contacts.getOrDefault("Plumber", "not saved"));

        contacts.put("Pizza", "555-0777");          // same key: the value is REPLACED
        contacts.remove("Dentist");
        System.out.println("Has Pizza? " + contacts.containsKey("Pizza") + ", size " + contacts.size());

        // Counting things: word frequency
        String review = "great food great service slow delivery great price";
        Map<String, Integer> counts = new TreeMap<>();      // TreeMap keeps keys sorted
        for (String word : review.split(" ")) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
            // shorter: counts.merge(word, 1, Integer::sum);
        }
        System.out.println("Word counts: " + counts);

        // Loop through key-value pairs
        Map<String, Double> menu = new TreeMap<>(Map.of("Tea", 2.0, "Coffee", 2.5, "Cake", 3.75));
        for (Map.Entry<String, Double> item : menu.entrySet()) {
            System.out.printf("%-7s $%.2f%n", item.getKey(), item.getValue());
        }
    }
}
