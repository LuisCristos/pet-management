package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(CustomerRequestDto customerRequestDto);

    Customer saveCustomer(Customer customer);

    List<CustomerResponseDto> getAllCustomers(String firstName, String lastName);

    CustomerResponseDto findCustomerById(Long customerId);

    void deleteCustomerById(Long customerId);

    Customer updateCustomer(Long customerId, CustomerRequestDto customerRequestDto);

    Customer returnCustomerIfExists(Long customerId);
}
