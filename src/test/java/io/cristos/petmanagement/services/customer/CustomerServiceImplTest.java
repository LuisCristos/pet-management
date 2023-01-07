package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.models.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class CustomerServiceImplTest {

    @BeforeEach
    void setUp() {
        CustomerRequest customerRequest = new CustomerRequest("Luis","Cristos",
                LocalDate.of(1976, Month.JANUARY, 26));
    }

    @Test
    void savePerson() {

        Customer customer = new Customer();

    }

    @Test
    void getAllPersons() {
    }

    @Test
    void findById() {
    }
}