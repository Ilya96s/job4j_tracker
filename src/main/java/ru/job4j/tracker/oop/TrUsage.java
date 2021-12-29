package ru.job4j.tracker.oop;

public class TrUsage {
    public static void main(String[] args) {
        Vehicle airplane = new Airplane();
        Vehicle bus = new Bus();
        Vehicle train = new Train();

        Vehicle[] vehicles = new Vehicle[] {airplane, bus, train};
        for (Vehicle v : vehicles) {
            v.move();
        }
    }
}
