package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.oop.ItemAscByName;
import ru.job4j.tracker.oop.ItemDescByName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void ascName() {
        List<Item> items = Arrays.asList(
                new Item("Ivan"),
                new Item("Sergey"),
                new Item("Mariya"),
                new Item("Oleg")
                );
        List<Item> expected = Arrays.asList(
                new Item("Ivan"),
                new Item("Mariya"),
                new Item("Oleg"),
                new Item("Sergey")
        );
        Collections.sort(items, new ItemAscByName());
        assertThat(items, is(expected));
    }

    @Test
    public void descName() {
        List<Item> items = Arrays.asList(
                new Item("Ivan"),
                new Item("Sergey"),
                new Item("Mariya"),
                new Item("Oleg")
        );
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item("Sergey"),
                new Item("Oleg"),
                new Item("Mariya"),
                new Item("Ivan")
        );
        assertThat(items, is(expected));
    }
}