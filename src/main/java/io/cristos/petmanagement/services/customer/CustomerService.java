package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(CustomerRequest customerRequest);

    List<CustomerRequest> getAllCustomers();

    CustomerRequest findCustomerById(Long id) throws NotFoundException;

    void deleteCustomerById(Long id) throws NotFoundException;

    Customer updateCustomer(Long id, CustomerRequest customerRequest) throws NotFoundException;

}
