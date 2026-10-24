package com.parkinglot.model;

/** A numbered spot on a floor. Encapsulated: only park() and leave() change it (Chapter 8.1). */
public class ParkingSpot {
    private final String id;
    private final int floor;
    private final SpotSize size;
    private final boolean hasCharger;
    private Vehicle occupant;

    public ParkingSpot(String id, int floor, SpotSize size, boolean hasCharger) {
        this.id = id;
        this.floor = floor;
        this.size = size;
        this.hasCharger = hasCharger;
    }

    public boolean isFree() { return occupant == null; }

    public boolean fits(Vehicle v) { return isFree() && size.canHold(v.requiredSize()); }

    public void park(Vehicle v) {
        if (!fits(v)) throw new IllegalStateException(v + " does not fit spot " + id);
        occupant = v;
    }

    public Vehicle leave() {
        Vehicle v = occupant;
        occupant = null;
        return v;
    }

    public String getId() { return id; }
    public int getFloor() { return floor; }
    public SpotSize getSize() { return size; }
    public boolean hasCharger() { return hasCharger; }
    public Vehicle getOccupant() { return occupant; }
}
