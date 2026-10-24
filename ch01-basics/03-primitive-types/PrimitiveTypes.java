// 1.3 Primitive data types: the eight built-in value types
// Run: java PrimitiveTypes.java
public class PrimitiveTypes {
    public static void main(String[] args) {
        // Whole numbers
        byte  floorNumber   = 12;               // -128 .. 127
        short yearBuilt     = 1998;             // about -32 thousand .. 32 thousand
        int   population    = 8_400_000;        // about -2.1 billion .. 2.1 billion (underscores help reading)
        long  worldPopulation = 8_100_000_000L; // very large; note the L at the end

        // Decimal numbers
        float  temperature = 36.6f;             // ~7 digits of precision; note the f
        double accountBalance = 10542.75;       // ~15 digits; the default for decimals

        // A single character, in single quotes
        char grade = 'A';

        // true or false
        boolean isOpen = true;

        System.out.println("byte    floorNumber     = " + floorNumber);
        System.out.println("short   yearBuilt       = " + yearBuilt);
        System.out.println("int     population      = " + population);
        System.out.println("long    worldPopulation = " + worldPopulation);
        System.out.println("float   temperature     = " + temperature);
        System.out.println("double  accountBalance  = " + accountBalance);
        System.out.println("char    grade           = " + grade);
        System.out.println("boolean isOpen          = " + isOpen);

        // Every type has limits
        System.out.println("Largest int:  " + Integer.MAX_VALUE);
        System.out.println("Largest long: " + Long.MAX_VALUE);

        // Overflow: going past the limit wraps around silently
        int odometer = Integer.MAX_VALUE;
        odometer = odometer + 1;
        System.out.println("MAX_VALUE + 1 = " + odometer + "  (overflow!)");
    }
}
