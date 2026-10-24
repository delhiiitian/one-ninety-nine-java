// 3.8 Classes and objects: blueprints and the things built from them
// Run: java ClassesAndObjects.java

public class ClassesAndObjects {
    public static void main(String[] args) {
        // Build two objects (instances) from the same blueprint
        Dog rex = new Dog();
        rex.name = "Rex";
        rex.breed = "Labrador";

        Dog bella = new Dog();
        bella.name = "Bella";
        bella.breed = "Beagle";

        // Each object keeps its own data
        rex.bark();
        bella.bark();
        rex.play(30);
        bella.play(10);
        rex.sleep();

        System.out.println(rex.name + " (" + rex.breed + ") energy: " + rex.energy);
        System.out.println(bella.name + " (" + bella.breed + ") energy: " + bella.energy);

        // Two variables can point to the SAME object
        Dog sameDog = rex;
        sameDog.name = "Rexy";
        System.out.println("rex.name is now: " + rex.name);
    }
}

// The blueprint: what every Dog HAS (fields) and DOES (methods)
class Dog {
    String name;
    String breed;
    int energy = 5;

    void bark() {
        System.out.println(name + " says: Woof!");
    }

    void play(int minutes) {
        energy -= minutes / 10;
        System.out.println(name + " played for " + minutes + " minutes. Energy now " + energy);
    }

    void sleep() {
        energy = 10;
        System.out.println(name + " took a nap. Energy restored to " + energy);
    }
}
