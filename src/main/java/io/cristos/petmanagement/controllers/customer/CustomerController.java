package io.cristos.petmanagement.controllers.customer;


import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerController {

    ResponseEntity<Customer> saveCustomer(CustomerRequest customerRequest);

    ResponseEntity<List<Customer>> getAllCustomers();

    ResponseEntity<Optional<Customer>> findCustomerById(Long id);

    ResponseEntity<Customer> deleteCustomerById(Long id);

    ResponseEntity<Customer> updateCustomer(Long id, String firstName, String lastName, LocalDate dateOfBirth);

}
