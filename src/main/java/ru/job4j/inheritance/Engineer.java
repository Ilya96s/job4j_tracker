package ru.job4j.inheritance;

public class Engineer extends  Profession {
    private String category;

    public Engineer(String name, String surname, String education, String birthday, String category) {
        super(name, surname, education, birthday);
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}