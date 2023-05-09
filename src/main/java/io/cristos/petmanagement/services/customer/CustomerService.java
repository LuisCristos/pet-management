package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface CustomerService {

    Customer saveCustomer(CustomerRequestDto customerRequestDto);

    Customer saveCustomer(Customer customer);

    Page<CustomerResponseDto> getAllCustomersPageSortFilter(int pageNumber, int pageSize,
                                                            String direction, String orderBy,
                                                            String searchValue, LocalDate birthdate);

    CustomerResponseDto findCustomerById(Long customerId);

    void deleteCustomerById(Long customerId);

    Customer updateCustomer(Long customerId, CustomerRequestDto customerRequestDto);

    Customer returnCustomerIfExists(Long customerId);
}
