# Project 8: Parking Lot System

Simulates a busy day at a two-floor mall car park: vehicles get the smallest spot that fits, electric cars that need power get a charger spot, fees follow a pluggable pricing strategy, and streams produce live reports.

```
p8-parking-lot/src/com/parkinglot/
├── model/
│   ├── Vehicle.java          abstract sealed parent
│   ├── Car, Motorcycle, Truck, ElectricCar
│   ├── Chargeable.java       interface (EVs)
│   ├── SpotSize.java         enum with canHold()
│   ├── ParkingSpot.java      encapsulated state: park() / leave()
│   ├── Ticket.java           record
│   └── Identifiable.java
├── pricing/
│   ├── PricingStrategy.java  functional interface
│   └── HourlyPricing.java    free 15 min, hourly rate, daily cap, weekend discount
├── service/
│   ├── Registry.java         generic store Registry<T extends Identifiable>
│   └── ParkingLot.java       spot finding, tickets, payments, stream reports
└── app/Main.java             the simulation
```

Run: `bash run.sh`. Test: `bash test.sh`.

**Uses (Chapter 8):** encapsulation, inheritance, polymorphism, abstract and sealed classes, interfaces with default methods, enums, generics, lambdas, streams, pattern matching.
