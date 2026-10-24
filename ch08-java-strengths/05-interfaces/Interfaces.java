// 8.5 Interfaces: a contract any class can sign
// Run: java Interfaces.java
import java.util.List;

public class Interfaces {
    public static void main(String[] args) {
        // Very different classes, one shared ability
        List<Chargeable> devices = List.of(new Phone(), new ElectricCar(), new Phone());
        for (Chargeable d : devices) {
            d.charge(30);
        }

        SmartSpeaker speaker = new SmartSpeaker();
        speaker.charge(10);
        speaker.play("Morning Jazz");
        System.out.println("Speaker battery warning: " + speaker.lowBatteryMessage());
    }
}

// The contract: anything Chargeable must have charge(minutes)
interface Chargeable {
    void charge(int minutes);

    // default method: a ready-made behaviour implementers get for free
    default String lowBatteryMessage() {
        return "Please plug in " + getClass().getSimpleName();
    }
}

interface MusicPlayer {
    void play(String playlist);
}

class Phone implements Chargeable {
    private int battery = 20;

    public void charge(int minutes) {
        battery = Math.min(100, battery + minutes * 2);
        System.out.println("Phone battery: " + battery + "%");
    }
}

class ElectricCar implements Chargeable {
    private int rangeKm = 50;

    public void charge(int minutes) {
        rangeKm += minutes * 5;
        System.out.println("Car range: " + rangeKm + " km");
    }
}

// A class can implement SEVERAL interfaces (but extend only one class)
class SmartSpeaker implements Chargeable, MusicPlayer {
    public void charge(int minutes) {
        System.out.println("Speaker charging for " + minutes + " min");
    }

    public void play(String playlist) {
        System.out.println("Playing " + playlist);
    }
}
