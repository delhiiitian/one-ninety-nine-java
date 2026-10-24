// 8.1 Encapsulation: hide the data, expose safe controls
// Run: java Encapsulation.java
public class Encapsulation {
    public static void main(String[] args) {
        SmartWatch watch = new SmartWatch("Lena");
        watch.addSteps(4500);
        watch.addSteps(3000);
        System.out.println(watch.getOwner() + " walked " + watch.getSteps() + " steps");

        try {
            watch.addSteps(-200);               // the class protects its own rules
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        watch.setDailyGoal(10_000);
        System.out.printf("Goal progress: %.0f%%%n", watch.goalProgress());
        // watch.steps = 999999;   // compile error: steps is private
    }
}

class SmartWatch {
    // 1. Fields are private: nobody outside can change them directly
    private final String owner;
    private int steps;
    private int dailyGoal = 8000;

    SmartWatch(String owner) {
        this.owner = owner;
    }

    // 2. Getters give read access
    public String getOwner() { return owner; }
    public int getSteps() { return steps; }

    // 3. Methods (and setters) change state only in valid ways
    public void addSteps(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Steps cannot be negative");
        }
        steps += count;
    }

    public void setDailyGoal(int goal) {
        if (goal < 1000) {
            throw new IllegalArgumentException("Goal too low");
        }
        this.dailyGoal = goal;
    }

    public double goalProgress() {
        return Math.min(100.0, steps * 100.0 / dailyGoal);
    }
}
