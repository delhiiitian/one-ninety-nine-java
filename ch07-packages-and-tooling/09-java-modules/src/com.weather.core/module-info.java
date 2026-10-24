// The module's "passport": its name, and which packages it shows to the world
module com.weather.core {
    exports com.weather.core;          // public API
    // com.weather.core.internal is NOT exported: hidden even though its classes are public
}
