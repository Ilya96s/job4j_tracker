package ru.job4j.stream;

import java.util.Objects;

public class Address {
    private String city;
    private String street;
    private int home;
    private int appartment;

    public Address(String city, String street, int home, int appartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.appartment = appartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return home == address.home
                && appartment == address.appartment
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, home, appartment);
    }
}