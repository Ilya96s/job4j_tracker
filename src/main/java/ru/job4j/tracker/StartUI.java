package ru.job4j.tracker;

import java.time.LocalDateTime;

public class StartUI {
    public static void main(String[] args) {
        Item item = new Item();
        item.getCreated();
        System.out.println("Текущие дата и время: " + item.getCreated());
    }
}
