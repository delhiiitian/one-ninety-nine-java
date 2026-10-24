package com.parkinglot.service;

import com.parkinglot.model.Chargeable;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.SpotSize;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.pricing.PricingStrategy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** The whole car park: finds spots, issues tickets, takes payment and reports (streams, Chapter 8.9). */
public class ParkingLot {
    private final List<ParkingSpot> spots = new ArrayList<>();
    private final Registry<Ticket> activeTickets = new Registry<>();
    private final List<Double> payments = new ArrayList<>();
    private PricingStrategy pricing;
    private int ticketCounter = 0;

    public ParkingLot(PricingStrategy pricing) {
        this.pricing = pricing;
    }

    public void addFloor(int floor, int small, int medium, int large, int chargers) {
        int n = 1;
        for (int i = 0; i < small; i++) spots.add(new ParkingSpot(floor + "-S" + n++, floor, SpotSize.SMALL, false));
        for (int i = 0; i < medium; i++) spots.add(new ParkingSpot(floor + "-M" + n++, floor, SpotSize.MEDIUM, i < chargers));
        for (int i = 0; i < large; i++) spots.add(new ParkingSpot(floor + "-L" + n++, floor, SpotSize.LARGE, false));
    }

    public void setPricing(PricingStrategy pricing) { this.pricing = pricing; }

    /** Best spot = smallest that fits, lowest floor first; EVs that need charge prefer a charger. */
    public Optional<Ticket> enter(Vehicle v, LocalDateTime time) {
        boolean wantsCharger = v instanceof Chargeable c && c.needsCharge();     // pattern matching
        Comparator<ParkingSpot> best = Comparator
                .comparing((ParkingSpot s) -> wantsCharger && !s.hasCharger())   // false (= has charger) sorts first
                .thenComparing(ParkingSpot::getSize)
                .thenComparingInt(ParkingSpot::getFloor);
        Optional<ParkingSpot> spot = spots.stream().filter(s -> s.fits(v)).min(best);
        return spot.map(s -> {
            s.park(v);
            Ticket t = new Ticket("T" + (++ticketCounter), v, s, time);
            activeTickets.add(t);
            return t;
        });
    }

    public double exit(String ticketId, LocalDateTime time) {
        Ticket t = activeTickets.remove(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown ticket " + ticketId));
        t.spot().leave();
        double fee = pricing.price(t, time);
        payments.add(fee);
        return fee;
    }

    public long freeSpots(SpotSize size) {
        return spots.stream().filter(s -> s.isFree() && s.getSize() == size).count();
    }

    public Map<Integer, Long> occupiedPerFloor() {
        return spots.stream().filter(s -> !s.isFree())
                .collect(Collectors.groupingBy(ParkingSpot::getFloor, TreeMap::new, Collectors.counting()));
    }

    public Map<String, Long> vehiclesByType() {
        return activeTickets.all().stream()
                .collect(Collectors.groupingBy(t -> t.vehicle().getClass().getSimpleName(), TreeMap::new, Collectors.counting()));
    }

    public double revenue() {
        return payments.stream().mapToDouble(Double::doubleValue).sum();
    }

    public int capacity() { return spots.size(); }
    public int parkedCount() { return activeTickets.size(); }
}
