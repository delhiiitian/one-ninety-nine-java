package com.parkinglot.model;

/** Spot sizes, smallest first. An enum with behaviour (Chapter 8.6). */
public enum SpotSize {
    SMALL, MEDIUM, LARGE;

    /** A vehicle fits any spot at least as big as it needs. */
    public boolean canHold(SpotSize needed) {
        return this.ordinal() >= needed.ordinal();
    }
}
