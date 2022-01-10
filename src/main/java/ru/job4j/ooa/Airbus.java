package ru.job4j.ooa;

import java.util.Scanner;

public final class Airbus extends Aircraft {
    private static final int COUNT_ENGINE = 2;

    private String name;

    public Airbus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printModel() {
        System.out.println("Модель самолета: " + name);
    }

    public void printCountEngine() {
        int engine = COUNT_ENGINE;
        if (name.equals("A380")) {
            engine = 4;
            System.out.println("Количество двигателе равно: " + engine);
        } else {
            System.out.println("Количество двигателей равно: " + COUNT_ENGINE);
        }
    }

    @Override
    public String toString() {
        return "Airbus{" +
                "name='" + name + '\'' +
                '}';
    }
}
