package com.parkinglot.model;

public non-sealed class Car extends Vehicle {
    public Car(String plate) { super(plate); }

    @Override public SpotSize requiredSize() { return SpotSize.MEDIUM; }
    @Override public double rateFactor() { return 1.0; }
}
