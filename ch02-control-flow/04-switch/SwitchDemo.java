// 2.4 switch: picking one path from a fixed menu
// Run: java SwitchDemo.java
public class SwitchDemo {
    public static void main(String[] args) {
        // Modern switch expression (Java 14+): arrow form, returns a value, no fall-through
        int dayNumber = 6;
        String dayName = switch (dayNumber) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6, 7 -> "Weekend";        // several labels share one result
            default -> "Unknown day";
        };
        System.out.println("Day " + dayNumber + " is " + dayName);

        // Switch on strings: a vending machine
        String button = "B2";
        double price = switch (button) {
            case "A1" -> 1.25;
            case "B2" -> 1.75;
            case "C3" -> {
                System.out.println("(C3 is the premium slot)");
                yield 2.50;                 // yield returns a value from a block
            }
            default -> 0.0;
        };
        System.out.println("Button " + button + " costs $" + price);

        // Classic switch statement: needs break, otherwise it FALLS THROUGH
        int trafficLight = 2;   // 1 red, 2 yellow, 3 green
        switch (trafficLight) {
            case 1:
                System.out.println("Stop");
                break;
            case 2:
                System.out.println("Slow down");
                // no break here: falls into case 3 as well!
            case 3:
                System.out.println("Go");
                break;
            default:
                System.out.println("Light broken");
        }
    }
}
