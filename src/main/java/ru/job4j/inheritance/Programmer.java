package ru.job4j.inheritance;

public class Programmer extends  Engineer {
    private boolean english;

    public Programmer(String name, String surname, String education, String birthday, String categoty, boolean english) {
        super(name, surname, education, birthday, categoty);
       this.english = english;
    }

    public boolean getEnglish() {
        return this.english;
    }
}