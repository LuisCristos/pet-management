package io.cristos.petmanagement.controllers.customer;


import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerController {

    ResponseEntity<Customer> saveCustomer(CustomerRequest customerRequest);

    ResponseEntity<List<Customer>> getAllCustomers();

    ResponseEntity<Customer> findCustomerById(Long id) throws NotFoundException;

    void deleteCustomerById(Long id);

}
