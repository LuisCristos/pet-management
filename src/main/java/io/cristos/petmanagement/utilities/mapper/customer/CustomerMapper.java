package io.cristos.petmanagement.utilities.mapper.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.models.customer.Customer;

import java.util.Collection;
import java.util.List;

public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);

    List<CustomerDto> customerListToCustomerDtoList(Collection<Customer> customerCollection);

}
