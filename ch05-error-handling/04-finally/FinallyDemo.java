// 5.4 finally: code that runs no matter what
// Run: java FinallyDemo.java
public class FinallyDemo {
    public static void main(String[] args) {
        rentCar(true);
        System.out.println();
        rentCar(false);
        System.out.println();
        System.out.println("Returned value: " + checkout());
    }

    static void rentCar(boolean tripGoesWell) {
        System.out.println("Picking up the rental car");
        try {
            System.out.println("Driving...");
            if (!tripGoesWell) {
                throw new IllegalStateException("flat tyre!");
            }
            System.out.println("Trip finished smoothly");
        } catch (IllegalStateException e) {
            System.out.println("Trouble: " + e.getMessage());
        } finally {
            // Like returning the car: it happens whether the trip went well or not
            System.out.println("Returning the car to the agency");
        }
    }

    static String checkout() {
        try {
            System.out.println("Processing payment");
            return "paid";                    // finally still runs before the method returns
        } finally {
            System.out.println("Closing the payment window");
        }
    }
}
