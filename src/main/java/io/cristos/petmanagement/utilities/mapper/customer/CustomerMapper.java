package io.cristos.petmanagement.utilities.mapper.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerPetResponseDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CustomerMapper {


    Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto);

    Customer customerRequestDtoToCustomer(Long customerId, CustomerRequestDto customerRequestDto);

    CustomerResponseDto customerToCustomerResponseDto(Customer customer);

    List<CustomerResponseDto> customerListToCustomerResponseDtoList(List<Customer> customerList);

    Customer createCustomerFromCustomerRequestDto(CustomerRequestDto customerRequestDto);

    CustomerPetResponseDto customerWithPetsToCustomerWithPetsDto(Customer customer);


    Page<CustomerResponseDto> pageCustomerToCustomerResponseDto(Page<Customer> customerPage);
}
