// 4.9 Records: compact classes that just carry data
// Run: java Records.java
import java.util.List;

public class Records {
    public static void main(String[] args) {
        Point home = new Point(3, 4);
        Point same = new Point(3, 4);

        // Records come with accessors, equals, hashCode and toString for free
        System.out.println("home = " + home);
        System.out.println("x = " + home.x() + ", y = " + home.y());
        System.out.println("home.equals(same)? " + home.equals(same));
        System.out.println("Distance from origin: " + home.distanceFromOrigin());

        // Validation in a compact constructor
        Temperature t = new Temperature(-10.0, "Oslo");
        System.out.println(t);
        try {
            new Temperature(-300.0, "Nowhere");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // Records are immutable: there are no setters. Make a changed copy instead.
        Point moved = new Point(home.x() + 1, home.y());
        System.out.println("moved = " + moved + ", home unchanged = " + home);

        // A list of records reads almost like a table
        List<Order> orders = List.of(
                new Order(101, "Keyboard", 2, 25.0),
                new Order(102, "Mouse", 1, 15.5));
        for (Order o : orders) {
            System.out.printf("#%d %-9s total $%.2f%n", o.id(), o.item(), o.total());
        }
    }
}

record Point(int x, int y) {
    double distanceFromOrigin() {                // records can have methods
        return Math.sqrt(x * x + y * y);
    }
}

record Temperature(double celsius, String city) {
    Temperature {                                 // compact constructor: runs before fields are set
        if (celsius < -273.15) {
            throw new IllegalArgumentException("Below absolute zero: " + celsius);
        }
    }
}

record Order(int id, String item, int quantity, double unitPrice) {
    double total() {
        return quantity * unitPrice;
    }
}
