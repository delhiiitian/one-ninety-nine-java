package com.parkinglot.model;

/** An ability, not a family: anything that can plug into a charger (Chapter 8.5). */
public interface Chargeable {
    int batteryPercent();

    default boolean needsCharge() {
        return batteryPercent() < 50;
    }
}
