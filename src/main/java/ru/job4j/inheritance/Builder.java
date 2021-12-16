package ru.job4j.inheritance;

public class Builder extends Engineer{
    private String typeOfWork;

    public Builder(String name, String surname, String education, String birthday, String categoty, String typeOfWork) {
        super(name, surname,education,birthday,categoty);
        this.typeOfWork = typeOfWork;
    }

    public String getTypeOfWork() {
        return this.typeOfWork;
    }
}
