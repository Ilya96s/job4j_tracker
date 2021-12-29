package ru.job4j.tracker.oop;

public class Train implements Vehicle {

    @Override
    public void move() {
        System.out.println("Поезд передвигается по рельсам");
    }
}
