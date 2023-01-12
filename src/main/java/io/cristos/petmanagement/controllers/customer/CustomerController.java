package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerController {

    ResponseEntity<CustomerRequest> saveCustomer(CustomerRequest customerRequest);

    ResponseEntity<List<CustomerRequest>> getAllCustomers();

    ResponseEntity<CustomerRequest> findCustomerById(Long id);

    ResponseEntity<CustomerRequest> deleteCustomerById(Long id);

    ResponseEntity<CustomerRequest> updateCustomer(Long id, CustomerRequest customerRequest);

}
