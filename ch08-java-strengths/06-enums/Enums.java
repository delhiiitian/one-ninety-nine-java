// 8.6 Enums: a fixed set of named options
// Run: java Enums.java
public class Enums {
    public static void main(String[] args) {
        Size mine = Size.LARGE;
        System.out.println("I ordered a " + mine + " coffee: " + mine.getMl() + " ml, $" + mine.getPrice());

        // Loop over every option
        for (Size s : Size.values()) {
            System.out.printf("%-6s %4d ml  $%.2f%n", s, s.getMl(), s.getPrice());
        }

        // Convert text to an enum (e.g. from user input or a file)
        Size fromText = Size.valueOf("SMALL");
        System.out.println("Parsed: " + fromText + ", position " + fromText.ordinal());

        // Enums and switch are a perfect match: the compiler knows every case
        OrderStatus status = OrderStatus.SHIPPED;
        String message = switch (status) {
            case PLACED -> "We received your order.";
            case SHIPPED -> "Your order is on the way.";
            case DELIVERED -> "Enjoy your purchase!";
            case CANCELLED -> "Your order was cancelled.";
        };
        System.out.println(message);
        System.out.println("Next status after PLACED: " + OrderStatus.PLACED.next());
    }
}

// Enums can have fields, constructors and methods
enum Size {
    SMALL(240, 2.50), MEDIUM(350, 3.20), LARGE(470, 3.90);

    private final int ml;
    private final double price;

    Size(int ml, double price) {
        this.ml = ml;
        this.price = price;
    }

    int getMl() { return ml; }
    double getPrice() { return price; }
}

enum OrderStatus {
    PLACED, SHIPPED, DELIVERED, CANCELLED;

    OrderStatus next() {
        return switch (this) {
            case PLACED -> SHIPPED;
            case SHIPPED, DELIVERED -> DELIVERED;
            case CANCELLED -> CANCELLED;
        };
    }
}
