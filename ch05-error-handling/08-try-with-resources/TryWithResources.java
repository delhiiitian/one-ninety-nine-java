// 5.8 try-with-resources: automatic cleanup
// Run: java TryWithResources.java
public class TryWithResources {
    public static void main(String[] args) {
        // Anything that implements AutoCloseable can go in the parentheses.
        // close() is called automatically, even if an exception happens.
        try (Faucet kitchen = new Faucet("kitchen")) {
            kitchen.pour(2);
        }
        System.out.println();

        try (Faucet bath = new Faucet("bathroom"); Faucet garden = new Faucet("garden")) {
            bath.pour(1);
            garden.pour(99);                  // fails, but both faucets still close
        } catch (IllegalArgumentException e) {
            System.out.println("Problem: " + e.getMessage());
        }
        // Resources close in REVERSE order of opening: garden first, then bathroom.
        // Files, network connections and database connections all work this way (Chapters 6 and 9).
    }
}

class Faucet implements AutoCloseable {
    private final String room;

    Faucet(String room) {
        this.room = room;
        System.out.println("Turn ON " + room + " faucet");
    }

    void pour(int litres) {
        if (litres > 10) {
            throw new IllegalArgumentException("Bucket overflow in " + room);
        }
        System.out.println("Poured " + litres + " litre(s) in " + room);
    }

    @Override
    public void close() {
        System.out.println("Turn OFF " + room + " faucet");
    }
}
