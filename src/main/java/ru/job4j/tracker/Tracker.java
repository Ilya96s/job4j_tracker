package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] rsl = new Item[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            Item name = items[i];
            if (name != null) {
                rsl[count] = name;
                count++;
            }
        }
        rsl = Arrays.copyOf(rsl, count);
        for (int i = 0; i < rsl.length; i++) {
            System.out.println(rsl[i]);
        }
        return rsl;
    }

    public Item[] findByName(String key) {
        Item[] rsl = new Item[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            Item name = items[i];
            if (name.getName().equals(key)) {
                rsl[count] = name;
                count++;
            }
        }
        rsl = Arrays.copyOf(rsl, count);
        for (int i = 0; i < rsl.length; i++) {
            System.out.println(rsl[i]);
        }
        return rsl;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int i = 0; i < size; i++) {
            if (items[i].getId() == id) {
                rsl = i;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            item.setId(id);
            items[index] = item;
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[size - 1] = null;
        size--;
        return true;
    }
}