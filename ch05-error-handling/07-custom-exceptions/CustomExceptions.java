// 5.7 Custom exceptions: naming your own problems
// Run: java CustomExceptions.java
import java.util.HashMap;
import java.util.Map;

public class CustomExceptions {
    public static void main(String[] args) {
        TicketOffice office = new TicketOffice();
        String[][] requests = {{"Coldplay", "2"}, {"Coldplay", "5"}, {"Adele", "1"}};

        for (String[] r : requests) {
            try {
                office.buy(r[0], Integer.parseInt(r[1]));
            } catch (SoldOutException e) {
                System.out.println("Sorry: " + e.getMessage() + " (only " + e.getRemaining() + " left)");
            } catch (UnknownEventException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        }
    }
}

// A checked exception: extends Exception. Callers must handle it.
class SoldOutException extends Exception {
    private final int remaining;

    SoldOutException(String event, int remaining) {
        super("Not enough tickets for " + event);
        this.remaining = remaining;
    }

    int getRemaining() {
        return remaining;
    }
}

// An unchecked exception: extends RuntimeException.
class UnknownEventException extends RuntimeException {
    UnknownEventException(String event) {
        super("No event called " + event);
    }
}

class TicketOffice {
    private final Map<String, Integer> available = new HashMap<>(Map.of("Coldplay", 4));

    void buy(String event, int count) throws SoldOutException {
        Integer left = available.get(event);
        if (left == null) {
            throw new UnknownEventException(event);
        }
        if (count > left) {
            throw new SoldOutException(event, left);
        }
        available.put(event, left - count);
        System.out.println("Bought " + count + " ticket(s) for " + event + ". Remaining: " + (left - count));
    }
}
