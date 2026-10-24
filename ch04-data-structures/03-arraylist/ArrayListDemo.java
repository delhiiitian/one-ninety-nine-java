// 4.3 ArrayList: a list that grows and shrinks
// Run: java ArrayListDemo.java
import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        // List<Type> holds objects of one type; use wrapper types for numbers (Integer, Double)
        List<String> shoppingList = new ArrayList<>();

        shoppingList.add("Milk");
        shoppingList.add("Eggs");
        shoppingList.add("Bread");
        shoppingList.add(1, "Butter");          // insert at index 1
        System.out.println("List: " + shoppingList);
        System.out.println("Size: " + shoppingList.size());
        System.out.println("Second item: " + shoppingList.get(1));

        shoppingList.set(0, "Oat milk");        // replace
        shoppingList.remove("Eggs");            // remove by value
        shoppingList.remove(0);                 // remove by index
        System.out.println("After changes: " + shoppingList);

        System.out.println("Has bread? " + shoppingList.contains("Bread"));
        System.out.println("Index of Bread: " + shoppingList.indexOf("Bread"));

        // Numbers: autoboxing turns int into Integer automatically
        List<Integer> steps = new ArrayList<>(List.of(4200, 8800, 10300, 6100));
        steps.add(12000);
        int total = 0;
        for (int s : steps) {
            total += s;
        }
        System.out.println("Weekly steps " + steps + " total " + total);

        // Remove items that match a condition
        steps.removeIf(s -> s < 7000);
        System.out.println("Active days only: " + steps);

        // List.of creates a fixed, read-only list
        List<String> weekdays = List.of("Mon", "Tue", "Wed", "Thu", "Fri");
        System.out.println(weekdays + " (read-only)");
        shoppingList.clear();
        System.out.println("Empty now? " + shoppingList.isEmpty());
    }
}
