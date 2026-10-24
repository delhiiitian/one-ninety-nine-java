// 4.6 HashSet: a collection with no duplicates
// Run: java HashSetDemo.java
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashSetDemo {
    public static void main(String[] args) {
        // Guest list at a door: each name gets in only once
        Set<String> guests = new HashSet<>();
        System.out.println("Add Ava: " + guests.add("Ava"));
        System.out.println("Add Leo: " + guests.add("Leo"));
        System.out.println("Add Ava again: " + guests.add("Ava"));     // false: already there
        System.out.println("Guest count: " + guests.size());
        System.out.println("Is Leo inside? " + guests.contains("Leo"));

        // Removing duplicates from a list of emails
        String[] signups = {"a@x.com", "b@x.com", "a@x.com", "c@x.com", "b@x.com"};
        Set<String> unique = new TreeSet<>();                           // TreeSet = sorted
        for (String email : signups) {
            unique.add(email);
        }
        System.out.println(signups.length + " signups, " + unique.size() + " unique: " + unique);

        // Set math: which friends like both pizza and sushi?
        Set<String> likesPizza = new TreeSet<>(Set.of("Ava", "Leo", "Mia", "Noah"));
        Set<String> likesSushi = new TreeSet<>(Set.of("Leo", "Noah", "Zoe"));

        Set<String> both = new TreeSet<>(likesPizza);
        both.retainAll(likesSushi);                  // intersection
        Set<String> either = new TreeSet<>(likesPizza);
        either.addAll(likesSushi);                   // union
        Set<String> pizzaOnly = new TreeSet<>(likesPizza);
        pizzaOnly.removeAll(likesSushi);             // difference

        System.out.println("Both:       " + both);
        System.out.println("Either:     " + either);
        System.out.println("Pizza only: " + pizzaOnly);
    }
}
