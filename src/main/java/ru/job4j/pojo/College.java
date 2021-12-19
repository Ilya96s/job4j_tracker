package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Иванов Иван Иванович");
        student.setGroup("ЭМм-51");
        student.setStartDate("2012.09.01");
        System.out.println("Студент: " + student.getFullName() + System.lineSeparator()
                + "Группа: " + student.getGroup() +System.lineSeparator()
                + "Дата поступления: " + student.getStartDate());
    }
}
