package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartUI {
    public static void main(String[] args) {
        Item item = new Item();
        System.out.println(item);
        LocalDateTime currentDateTime = item.getCreated();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTimeFormat = currentDateTime.format(formater);
        System.out.println("Текущие дата и время: " + currentDateTimeFormat);
    }
}
