package ru.job4j.collection;

import org.junit.Test;

import java.sql.PseudoColumnUsage;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PassportOfficeTest {

    @Test
    public void whenAdd() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport()), is(citizen));
    }

    @Test
    public void whenNotAdd() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizen1 = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen1);
        assertFalse(office.add(citizen1));
    }
}