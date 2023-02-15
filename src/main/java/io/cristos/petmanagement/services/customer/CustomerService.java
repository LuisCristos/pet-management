package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.models.customer.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto findCustomerById(Long customerId);

    void deleteCustomerById(Long customerId);

    Customer updateCustomer(Long customerId, CustomerDto customerDto);

    Customer returnCustomerIfExists(Long customerId, String action);
}
