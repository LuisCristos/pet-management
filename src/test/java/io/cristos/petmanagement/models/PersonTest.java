package io.cristos.petmanagement.models;

import io.cristos.petmanagement.models.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

class PersonTest {

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person(1L, "Luis", "Cristos", LocalDate.of(1976, Month.JANUARY, 26));

    }

    @Test
    void testPerson() {
        assertEquals(person.getId(), 1L, "Wrong id");
        assertEquals(person.getFirstName(), "Luis", "Wrong Firstname");
        assertEquals(person.getLastName(), "Cristos", "Wrong Lastname");
        assertEquals(person.getDateOfBirth(), LocalDate.of(1976, Month.JANUARY, 26), "Wrong Birthdate");
    }
}