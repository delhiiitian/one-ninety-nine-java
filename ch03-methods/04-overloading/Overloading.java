// 3.4 Method overloading: same name, different parameter lists
// Run: java Overloading.java
public class Overloading {

    // Area of a square
    static double area(double side) {
        return side * side;
    }

    // Area of a rectangle (two parameters)
    static double area(double width, double height) {
        return width * height;
    }

    // Area of a circle, identified by a different parameter type
    static double area(int radius) {
        return Math.PI * radius * radius;
    }

    static void log(String message) {
        log(message, "INFO");          // one overload can call another
    }

    static void log(String message, String level) {
        System.out.println("[" + level + "] " + message);
    }

    public static void main(String[] args) {
        // Java picks the right version from the arguments you pass
        System.out.println("Square 4.0:       " + area(4.0));
        System.out.println("Rectangle 3 x 5:  " + area(3.0, 5.0));
        System.out.printf("Circle radius 2:  %.2f%n", area(2));

        log("Server started");
        log("Disk almost full", "WARN");

        // The JDK uses overloading everywhere: println accepts many types
        System.out.println(42);
        System.out.println(3.14);
        System.out.println('Z');
        System.out.println(true);
    }
}
