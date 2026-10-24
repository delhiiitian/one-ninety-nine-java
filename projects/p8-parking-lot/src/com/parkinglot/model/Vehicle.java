package com.parkinglot.model;

/**
 * Every vehicle has a plate and needs a spot size. Abstract: nobody parks a plain "Vehicle" (Chapter 8.4).
 * Sealed: only the listed classes may extend it, so the compiler knows every kind.
 */
public abstract sealed class Vehicle permits Car, Motorcycle, Truck, ElectricCar {
    private final String plate;

    protected Vehicle(String plate) {
        if (plate == null || plate.isBlank()) throw new IllegalArgumentException("Plate required");
        this.plate = plate.toUpperCase();
    }

    public String getPlate() { return plate; }

    /** Each subclass answers differently: polymorphism (Chapter 8.3). */
    public abstract SpotSize requiredSize();

    /** Rate multiplier: a truck pays more than a motorcycle. */
    public abstract double rateFactor();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + plate + ")";
    }
}
