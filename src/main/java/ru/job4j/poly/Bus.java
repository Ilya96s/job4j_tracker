package ru.job4j.poly;

public class Bus implements Transport {

    private int passengers;

    @Override
    public void drive() {
        System.out.println("Автобус № 10");
    }

    @Override
    public void passengers(int passengers) {
        this.passengers = passengers;
    }

    @Override
    public float fuel(float fuel) {
        float price = fuel * 50.F;
        return price;
    }
}
