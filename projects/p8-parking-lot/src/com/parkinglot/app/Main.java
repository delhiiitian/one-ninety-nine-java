package com.parkinglot.app;

import com.parkinglot.model.Car;
import com.parkinglot.model.ElectricCar;
import com.parkinglot.model.Motorcycle;
import com.parkinglot.model.SpotSize;
import com.parkinglot.model.Ticket;
import com.parkinglot.model.Truck;
import com.parkinglot.model.Vehicle;
import com.parkinglot.pricing.HourlyPricing;
import com.parkinglot.service.ParkingLot;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Project 8: Parking Lot System. Simulates one busy Friday at a two-floor mall car park. */
public class Main {

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(new HourlyPricing(3.0, 20.0));
        lot.addFloor(0, 2, 3, 1, 1);      // ground: 2 small, 3 medium (1 charger), 1 large
        lot.addFloor(1, 1, 2, 0, 0);      // first floor: 1 small, 2 medium
        System.out.println("Mall car park open with " + lot.capacity() + " spots.");

        LocalDateTime friday = LocalDateTime.of(2026, 10, 23, 9, 0);
        Map<String, String> ticketOf = new HashMap<>();
        List<Vehicle> arrivals = List.of(
                new Car("KA01AB1111"), new Motorcycle("KA02MC2222"), new ElectricCar("KA03EV3333", 20),
                new Truck("KA04TR4444"), new Truck("KA05TR5555"), new Car("KA06CR6666"),
                new Car("KA07CR7777"), new Car("KA08CR8888"), new Car("KA09CR9999"));

        int minute = 0;
        for (Vehicle v : arrivals) {
            Optional<Ticket> t = lot.enter(v, friday.plusMinutes(minute += 10));
            if (t.isPresent()) {
                ticketOf.put(v.getPlate(), t.get().id());
                System.out.printf("%-24s -> spot %-5s ticket %s%n", v, t.get().spot().getId(), t.get().id());
            } else {
                System.out.printf("%-24s -> FULL, please try later%n", v);
            }
        }

        System.out.println("Occupied per floor: " + lot.occupiedPerFloor());
        System.out.println("Parked by type: " + lot.vehiclesByType());
        System.out.println("Free medium spots: " + lot.freeSpots(SpotSize.MEDIUM));

        // Departures through the day
        pay(lot, ticketOf.get("KA02MC2222"), friday.plusMinutes(30), "Motorcycle, quick stop");
        pay(lot, ticketOf.get("KA01AB1111"), friday.plusHours(3), "Car, 3 hours of shopping");
        pay(lot, ticketOf.get("KA04TR4444"), friday.plusHours(2), "Truck, delivery");
        pay(lot, ticketOf.get("KA03EV3333"), friday.plusHours(14), "EV, all day (capped)");

        // Pricing is pluggable: a flat event-night price, written as a lambda
        lot.setPricing((ticket, exit) -> 5.0);
        pay(lot, ticketOf.get("KA06CR6666"), friday.plusHours(12), "Car, concert night flat rate");

        try {
            lot.exit("T999", friday.plusHours(1));
        } catch (IllegalArgumentException e) {
            System.out.println("Gate error: " + e.getMessage());
        }

        System.out.printf("Still parked: %d, revenue today: $%.2f%n", lot.parkedCount(), lot.revenue());
    }

    static void pay(ParkingLot lot, String ticketId, LocalDateTime when, String label) {
        System.out.printf("Exit %-4s %-30s pays $%5.2f%n", ticketId, label, lot.exit(ticketId, when));
    }
}
