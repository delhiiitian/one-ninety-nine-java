package com.parkinglot.pricing;

import com.parkinglot.model.Ticket;
import java.time.LocalDateTime;

/**
 * How much to charge. A functional interface, so a pricing rule can also be a lambda (Chapter 8.8).
 * Swapping strategies changes prices without touching the parking code (the Strategy pattern).
 */
@FunctionalInterface
public interface PricingStrategy {
    double price(Ticket ticket, LocalDateTime exit);
}
