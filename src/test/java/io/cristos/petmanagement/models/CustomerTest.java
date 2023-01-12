package io.cristos.petmanagement.models;

import io.cristos.petmanagement.models.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(1L, "Luis", "Cristos",
                LocalDate.of(1976, Month.JANUARY, 26));

    }

    @Test
    void testPerson() {
        assertEquals(customer.getId(), 1L, "Wrong id");
        assertEquals(customer.getFirstName(), "Luis", "Wrong Firstname");
        assertEquals(customer.getLastName(), "Cristos", "Wrong Lastname");
        assertEquals(customer.getDateOfBirth(), LocalDate.of(1976, Month.JANUARY, 26), "Wrong Birthdate");
    }
}