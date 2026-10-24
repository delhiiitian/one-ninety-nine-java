// 7.7 Logging: a flight recorder for your program
// Run: java LoggingDemo.java
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggingDemo {

    private static final Logger LOG = Logger.getLogger("shop.checkout");

    public static void main(String[] args) {
        setUpSimpleConsoleLogging(Level.FINE);

        LOG.info("Checkout service starting");
        processOrder("A-100", 3);
        processOrder("A-101", 0);

        // Raise the threshold: FINE messages disappear without touching the code that logs them
        System.out.println("--- switching to WARNING level ---");
        LOG.setLevel(Level.WARNING);
        processOrder("A-102", 2);
        processOrder("A-103", -1);
    }

    static void processOrder(String id, int quantity) {
        LOG.fine("Validating order " + id);                       // detail for developers
        if (quantity <= 0) {
            LOG.warning("Order " + id + " has invalid quantity " + quantity);
            return;
        }
        LOG.info("Order " + id + " accepted for " + quantity + " item(s)");
    }

    // Normally configured in a logging.properties file; done in code here to keep output tidy.
    static void setUpSimpleConsoleLogging(Level level) {
        Logger root = Logger.getLogger("");
        for (var h : root.getHandlers()) root.removeHandler(h);
        ConsoleHandler handler = new ConsoleHandler() {
            { setOutputStream(System.out); }
        };
        handler.setLevel(Level.ALL);
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord r) {
                return String.format("%-7s [%s] %s%n", r.getLevel(), r.getLoggerName(), r.getMessage());
            }
        });
        root.addHandler(handler);
        LOG.setLevel(level);
    }
}
