package ru.job4j.function;

import org.w3c.dom.ls.LSOutput;

import java.util.function.Function;
import java.util.function.Predicate;

public class StrategyUsage {
    public boolean check(Predicate<String> pred, String s) {
        return pred.test(s);
    }

    public boolean empty(String s) {
        return check(str -> str.isEmpty(), s);
    }

    public boolean startWith(String s, String pref) {
        return check(str -> str.startsWith(pref), s);
    }

    public boolean contains(String s, String key) {
        return check(str -> str.contains(key), s);
    }

    public String transform(Function<String, String> fun, String s) {
        return fun.apply(s);
    }

    public static void main(String[] args) {
        StrategyUsage usage = new StrategyUsage();
        System.out.println("Резульат работы: " + usage.check(str -> str.isEmpty(), ""));
        System.out.println("Резульать работы: " + usage.check(str -> str.startsWith("Fun"), "Functional interface"));
        System.out.println("Результат работы: " + usage.check(str -> str.contains("rn"), "Surname name"));
        System.out.println("Строка после преобразования: " + usage.transform(str -> str.toUpperCase(), "Aafewwrtg"));
        System.out.println(usage.transform(str -> str.concat("работает корректно"), "Строка после преобразования"));
        System.out.println("Строка после преобразования: " + usage.transform(str -> str.trim(), "   aBC dEfghK Lmnp RstU        "));
    }
}