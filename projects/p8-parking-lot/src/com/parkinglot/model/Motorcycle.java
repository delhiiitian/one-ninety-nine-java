package com.parkinglot.model;

public non-sealed class Motorcycle extends Vehicle {
    public Motorcycle(String plate) { super(plate); }

    @Override public SpotSize requiredSize() { return SpotSize.SMALL; }
    @Override public double rateFactor() { return 0.5; }
}
