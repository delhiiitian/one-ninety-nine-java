package com.parkinglot.model;

import java.time.Duration;
import java.time.LocalDateTime;

/** Issued at the entry gate. A record because a ticket never changes (Chapter 4.9). */
public record Ticket(String id, Vehicle vehicle, ParkingSpot spot, LocalDateTime entry) implements Identifiable {

    public long minutesUntil(LocalDateTime exit) {
        return Duration.between(entry, exit).toMinutes();
    }
}
