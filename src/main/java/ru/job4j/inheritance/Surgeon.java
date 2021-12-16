package ru.job4j.inheritance;

public class Surgeon extends Doctor {
    private String operationTime;

    public Surgeon(String name, String surname, String education, String birthday,String specialization, String operationTime) {
        super(name,surname,education,birthday,specialization);
        this.operationTime = operationTime;
    }

    public String getOperationTime() {
        return this.operationTime;
    }
}
