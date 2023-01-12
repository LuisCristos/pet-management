package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto findCustomerById(Long id) throws NotFoundException;

    void deleteCustomerById(Long id) throws NotFoundException;

    Customer updateCustomer(Long id, CustomerDto customerDto) throws NotFoundException;

}
