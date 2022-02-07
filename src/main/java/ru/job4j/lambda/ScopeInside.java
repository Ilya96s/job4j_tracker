package ru.job4j.lambda;

import java.util.function.Supplier;

public class ScopeInside {
    public static void main(String[] args) {
        int[] number = {1, 2, 3};
        int total = 0;
        for (int i = 0; i < number.length; i++) {
            int num = i;
            int total1 = total;
            total = add(
                    () -> total1 + number[num]
            );
        }
        System.out.println(total);
    }

    private static Integer add(Supplier<Integer> calc) {
        return calc.get();
    }
}