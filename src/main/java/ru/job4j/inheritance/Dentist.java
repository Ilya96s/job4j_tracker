package ru.job4j.inheritance;

public class Dentist extends  Doctor {
    private String patientName;

    public Dentist(String name, String surname, String education, String birthday, String specialization, String patientName) {
        super(name, surname, education, birthday, specialization);
        this.patientName = patientName;
    }

    public String getPatientName() {
        return this.patientName;
    }
}