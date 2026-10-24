// 2.1 Booleans, comparisons and logical operators
// Run: java BooleansAndComparisons.java
public class BooleansAndComparisons {
    public static void main(String[] args) {
        int age = 19;
        int heightCm = 128;
        boolean hasTicket = true;

        // Comparison operators produce true or false
        System.out.println("age == 19 : " + (age == 19));
        System.out.println("age != 18 : " + (age != 18));
        System.out.println("age > 21  : " + (age > 21));
        System.out.println("age >= 18 : " + (age >= 18));
        System.out.println("height < 120 : " + (heightCm < 120));

        // Logical operators combine conditions
        boolean canRideAlone = age >= 16 && heightCm >= 120;   // AND: both must be true
        boolean getsDiscount = age < 12 || age >= 65;           // OR: at least one true
        boolean needsTicket = !hasTicket;                       // NOT: flips the value

        System.out.println("Can ride alone? " + canRideAlone);
        System.out.println("Gets discount?  " + getsDiscount);
        System.out.println("Needs ticket?   " + needsTicket);

        // Short-circuit: the right side is skipped when the answer is already known
        String coupon = null;
        boolean validCoupon = coupon != null && coupon.length() == 8;   // no crash
        System.out.println("Valid coupon? " + validCoupon);

        // Store the result of a complex check in a well-named variable
        boolean isWeekend = true;
        boolean isHoliday = false;
        boolean parkIsBusy = (isWeekend || isHoliday) && hasTicket;
        System.out.println("Park is busy for you? " + parkIsBusy);
    }
}
