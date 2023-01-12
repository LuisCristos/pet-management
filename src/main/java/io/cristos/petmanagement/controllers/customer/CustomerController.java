package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerController {

    ResponseEntity<CustomerDto> saveCustomer(CustomerDto customerDto);

    ResponseEntity<List<CustomerDto>> getAllCustomers();

    ResponseEntity<CustomerDto> findCustomerById(Long id);

    ResponseEntity<CustomerDto> deleteCustomerById(Long id);

    ResponseEntity<CustomerDto> updateCustomer(Long id, CustomerDto customerDto);

}
