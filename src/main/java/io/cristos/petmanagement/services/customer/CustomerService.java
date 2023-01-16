package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.models.customer.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto findCustomerById(Long id);

    void deleteCustomerById(Long id);

    Customer updateCustomer(Long id, CustomerDto customerDto);

}
