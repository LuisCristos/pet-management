package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer saveCustomer(CustomerRequest customerRequest);

    List<Customer> getAllCustomers();

    Optional<Customer> findCustomerById(Long id) throws NotFoundException;

    void deleteCustomerById(Long id) throws NotFoundException;

    Customer updateCustomer(Long id, String firstName, String lastName, LocalDate dateOfBirth) throws NotFoundException;

}
