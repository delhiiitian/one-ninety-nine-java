// 7.8 Javadoc: documentation written next to the code
// Run: java Thermostat.java        Generate HTML docs: bash run.sh

/**
 * A smart home thermostat that keeps a room near a target temperature.
 *
 * <p>Example:
 * <pre>
 *   Thermostat t = new Thermostat(21.0);
 *   t.decide(18.5);   // returns "HEAT"
 * </pre>
 *
 * @author Java 199
 * @version 1.0
 */
public class Thermostat {

    /** How far (in degrees C) the room may drift before the system reacts. */
    public static final double TOLERANCE = 0.5;

    private final double target;

    /**
     * Creates a thermostat.
     *
     * @param target the desired room temperature in degrees Celsius
     * @throws IllegalArgumentException if target is outside 5 to 30 degrees
     */
    public Thermostat(double target) {
        if (target < 5 || target > 30) {
            throw new IllegalArgumentException("Target out of range: " + target);
        }
        this.target = target;
    }

    /**
     * Decides what the heating system should do.
     *
     * @param current the temperature measured right now, in degrees Celsius
     * @return {@code "HEAT"}, {@code "COOL"} or {@code "IDLE"}
     */
    public String decide(double current) {
        if (current < target - TOLERANCE) return "HEAT";
        if (current > target + TOLERANCE) return "COOL";
        return "IDLE";
    }

    /**
     * Demo entry point.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Thermostat t = new Thermostat(21.0);
        for (double reading : new double[]{18.5, 21.2, 23.0}) {
            System.out.println(reading + " C -> " + t.decide(reading));
        }
    }
}
