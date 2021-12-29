package ru.job4j.tracker.oop;

public class College {
    public static void main(String[] args) {
        Freshman freshman = new Freshman();
        Students students = freshman;
        Object object = students;
    }
}
