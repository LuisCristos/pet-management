package io.cristos.petmanagement.controllers.customer;


import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.models.customer.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerController {

    ResponseEntity<Customer> saveCustomer(@Valid CustomerRequest customerRequest);

    ResponseEntity<List<Customer>> getAllCustomers();

    ResponseEntity<Optional<Customer>> findCustomerById(Long id);

    ResponseEntity<Customer> deleteCustomerById(Long id);

    ResponseEntity<Customer> updateCustomer(
            Long id,
            @RequestParam() @Size(min = 2, max = 255, message = "Firstname must be between 2 and 255 characters long") String firstName,
            @RequestParam() String lastName,
            @RequestParam() LocalDate dateOfBirth);

}
