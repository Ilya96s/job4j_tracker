package ru.job4j.tracker.oop;

public class Bus implements Vehicle {

    @Override
    public void move() {
        System.out.println("Автобус двигается по скоростным трассам");
    }
}
