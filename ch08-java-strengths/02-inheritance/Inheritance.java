// 8.2 Inheritance: building on an existing class
// Run: java Inheritance.java
public class Inheritance {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 2022, 5);
        Truck truck = new Truck("Volvo", 2019, 18.0);

        car.start();                  // inherited from Vehicle
        car.openTrunk();              // Car's own method
        truck.start();
        truck.load(12.5);

        System.out.println(car.describe());
        System.out.println(truck.describe());
        System.out.println("Is a Truck a Vehicle? " + (truck instanceof Vehicle));
    }
}

// Parent (superclass): what every vehicle shares
class Vehicle {
    protected final String brand;
    protected final int year;

    Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    void start() {
        System.out.println(brand + " engine starts");
    }

    String describe() {
        return year + " " + brand;
    }
}

// Child (subclass): "a Car IS A Vehicle", plus extra details
class Car extends Vehicle {
    private final int seats;

    Car(String brand, int year, int seats) {
        super(brand, year);           // call the parent constructor first
        this.seats = seats;
    }

    void openTrunk() {
        System.out.println(brand + " trunk opened");
    }

    @Override
    String describe() {
        return super.describe() + " car with " + seats + " seats";   // extend the parent's version
    }
}

class Truck extends Vehicle {
    private final double maxTonnes;

    Truck(String brand, int year, double maxTonnes) {
        super(brand, year);
        this.maxTonnes = maxTonnes;
    }

    void load(double tonnes) {
        System.out.println(tonnes <= maxTonnes ? "Loaded " + tonnes + " t" : "Too heavy!");
    }

    @Override
    String describe() {
        return super.describe() + " truck, max " + maxTonnes + " t";
    }
}
