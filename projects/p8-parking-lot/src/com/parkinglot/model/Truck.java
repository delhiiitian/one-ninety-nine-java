package com.parkinglot.model;

public non-sealed class Truck extends Vehicle {
    public Truck(String plate) { super(plate); }

    @Override public SpotSize requiredSize() { return SpotSize.LARGE; }
    @Override public double rateFactor() { return 2.0; }
}
