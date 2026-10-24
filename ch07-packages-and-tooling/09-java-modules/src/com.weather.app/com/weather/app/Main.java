// 7.9 Java modules (module-info.java)
// Run: bash run.sh
package com.weather.app;

import com.weather.core.Forecast;
// import com.weather.core.internal.Sensor;   // compile error: package is not exported

public class Main {
    public static void main(String[] args) {
        System.out.println(Forecast.today("Lisbon"));
        System.out.println(Forecast.today("Cairo"));
        System.out.println("Running in module: " + Main.class.getModule().getName());
    }
}
