package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] leftRsl = left.split(". Task.");
        String[] rightRsl = right.split(". Task.");
        int l = Integer.parseInt(leftRsl[0]);
        int r = Integer.parseInt(rightRsl[0]);
        return Integer.compare(l, r);
    }
}
