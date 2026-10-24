package com.parkinglot.pricing;

import com.parkinglot.model.Ticket;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

/** First 15 minutes free, then a rate per started hour, capped per day; weekends 20% off. */
public class HourlyPricing implements PricingStrategy {
    private final double ratePerHour;
    private final double dailyCap;

    public HourlyPricing(double ratePerHour, double dailyCap) {
        this.ratePerHour = ratePerHour;
        this.dailyCap = dailyCap;
    }

    @Override
    public double price(Ticket t, LocalDateTime exit) {
        long minutes = t.minutesUntil(exit);
        if (minutes <= 15) return 0;
        long hours = (minutes + 59) / 60;                       // every started hour counts
        long days = hours / 24;
        double cost = days * dailyCap + Math.min((hours % 24) * ratePerHour * t.vehicle().rateFactor(), dailyCap);
        DayOfWeek day = t.entry().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) cost *= 0.8;
        return Math.round(cost * 100) / 100.0;
    }
}
