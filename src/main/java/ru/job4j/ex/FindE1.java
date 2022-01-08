package ru.job4j.ex;

public class FindE1 {
    public static int indexOF(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                rsl = i;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Element not found");
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] value = {"one", "two", "three", "four", "five"};
        try {
            System.out.println(FindE1.indexOF(value, "five"));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
