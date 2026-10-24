package com.weather.core;

import com.weather.core.internal.Sensor;

public class Forecast {
    public static String today(String city) {
        double temp = Sensor.read(city);
        return city + ": " + temp + " C, " + (temp > 25 ? "sunny" : "mild");
    }
}
