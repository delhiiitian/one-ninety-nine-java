// 9.3 Regular expressions: search patterns for text
// Run: java RegularExpressions.java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {
    public static void main(String[] args) {
        // Validation: does the WHOLE text fit the pattern?
        String emailRule = "[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}";
        for (String e : new String[]{"sam@mail.com", "sam@mail", "first.last+news@shop.co"}) {
            System.out.println(e + " valid? " + e.matches(emailRule));
        }

        // Postal codes like Indian PIN (6 digits, not starting with 0)
        System.out.println("560001 is a PIN? " + "560001".matches("[1-9]\\d{5}"));

        // Searching: find every match inside a longer text
        String invoice = "Order #1042 paid 2026-10-01, order #1077 paid 2026-10-19.";
        Matcher orders = Pattern.compile("#(\\d+)").matcher(invoice);
        while (orders.find()) {
            System.out.println("Found order number " + orders.group(1));
        }

        // Named groups pull out parts of a date
        Pattern date = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");
        Matcher m = date.matcher(invoice);
        while (m.find()) {
            System.out.println("Day " + m.group("day") + " of month " + m.group("month") + ", " + m.group("year"));
        }

        // Replacing: hide all but the last 4 digits of card numbers
        String log = "Card 4111222233334444 charged; card 5500000000000004 declined";
        System.out.println(log.replaceAll("\\d{12}(\\d{4})", "************$1"));

        // Splitting on messy separators
        String tags = "java, spring ;  docker,kubernetes";
        System.out.println(String.join(" | ", tags.split("\\s*[,;]\\s*")));
    }
}
