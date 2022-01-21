package ru.job4j.collection;

import java.util.ArrayList;

public class UsageArrayList {
    public static void main(String[] args) {
        ArrayList<String> person = new ArrayList<String>();
        person.add("Petr");
        person.add("Ivan");
        person.add("Stepan");
        for (String obj : person) {
            System.out.println(obj);
        }
    }
}