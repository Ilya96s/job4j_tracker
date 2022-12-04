package ru.job4j.tracker;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Item {

    private int id;

    private String name;

    private LocalDateTime created = LocalDateTime.now();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int id, LocalDateTime created) {
        this.name = name;
        this.id = id;
        this.created = created;
    }
}
