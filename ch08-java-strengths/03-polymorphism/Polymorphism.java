// 8.3 Polymorphism: one call, many behaviours
// Run: java Polymorphism.java
import java.util.List;

public class Polymorphism {
    public static void main(String[] args) {
        // One list, many kinds of payment. Each variable is typed as the PARENT.
        List<Payment> checkoutQueue = List.of(
                new CardPayment(49.99, "4111222233334444"),
                new UpiPayment(15.00, "ravi@bank"),
                new CashPayment(20.00, 50.00));

        // The same call runs a different method depending on the real object
        for (Payment p : checkoutQueue) {
            p.process();
        }

        // Pattern matching for instanceof (Java 16+): check and cast in one step
        for (Payment p : checkoutQueue) {
            if (p instanceof CashPayment cash) {
                System.out.println("Change due: $" + cash.change());
            }
        }
    }
}

class Payment {
    protected final double amount;

    Payment(double amount) { this.amount = amount; }

    void process() {
        System.out.println("Processing generic payment of $" + amount);
    }
}

class CardPayment extends Payment {
    private final String cardNumber;

    CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    void process() {
        System.out.println("Charging $" + amount + " to card ending " + cardNumber.substring(12));
    }
}

class UpiPayment extends Payment {
    private final String upiId;

    UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    void process() {
        System.out.println("Requesting $" + amount + " from UPI id " + upiId);
    }
}

class CashPayment extends Payment {
    private final double handedOver;

    CashPayment(double amount, double handedOver) {
        super(amount);
        this.handedOver = handedOver;
    }

    double change() { return handedOver - amount; }

    @Override
    void process() {
        System.out.println("Received $" + handedOver + " cash for a $" + amount + " bill");
    }
}
