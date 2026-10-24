package com.parkinglot.model;

/** IS-A Vehicle (inheritance) and CAN charge (interface). */
public final class ElectricCar extends Vehicle implements Chargeable {
    private final int battery;

    public ElectricCar(String plate, int battery) {
        super(plate);
        this.battery = battery;
    }

    @Override public SpotSize requiredSize() { return SpotSize.MEDIUM; }
    @Override public double rateFactor() { return 1.0; }
    @Override public int batteryPercent() { return battery; }
}
