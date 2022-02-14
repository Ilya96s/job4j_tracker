package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentLevel {
    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .filter(s -> s != null)
                .sorted(Comparator.comparing(Student::getScore))
                .takeWhile(s -> s.getScore() > bound)
                .collect(Collectors.toList());
    }
}
