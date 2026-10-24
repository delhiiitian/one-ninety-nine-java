package com.weather.core.internal;

public class Sensor {
    public static double read(String city) {
        return 18 + Math.abs(city.hashCode() % 15);   // fake reading for the demo
    }
}
