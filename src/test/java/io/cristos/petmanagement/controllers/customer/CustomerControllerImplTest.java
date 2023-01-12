package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import io.cristos.petmanagement.services.customer.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class CustomerControllerImplTest {

    CustomerControllerImpl customerController;
    CustomerServiceImpl customerService;
    CustomerRepository customerRepository;
    CustomerRequest customerRequest;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest(
                "Fred",
                "Jupiter",
                LocalDate.of(1976, Month.JANUARY, 26));
    }

    @Test
    void saveCustomer() {
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void findCustomerById() {
    }

    @Test
    void deleteCustomerById() {
    }

    @Test
    void updateCustomer() {
    }
}