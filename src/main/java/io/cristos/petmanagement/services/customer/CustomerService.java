package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer saveCustomer(CustomerRequestDto customerRequestDto);

    Customer saveCustomer(Customer customer);

    Page<CustomerResponseDto> getAllCustomersPageSortFilter(Pageable pageable, String searchValue);

    CustomerResponseDto findCustomerById(Long customerId);

    void deleteCustomerById(Long customerId);

    Customer updateCustomer(Long customerId, CustomerRequestDto customerRequestDto);

    Customer returnCustomerIfExists(Long customerId);

}
