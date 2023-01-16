package io.cristos.petmanagement.utilities.mapper.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setDateOfCreation(customer.getDateOfCreation());

        return customerDto;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        customer.setDateOfCreation(customerDto.getDateOfCreation());

        return customer;
    }

    @Override
    public List<CustomerDto> customerListToCustomerDtoList(Collection<Customer> customerCollection) {

        List<CustomerDto> customercustomerDtosList = new ArrayList<>();

        for (Customer customer : customerCollection) {

            CustomerDto customerDto = customerToCustomerDto(customer);

            customercustomerDtosList.add(customerDto);
        }

        return customercustomerDtosList;
    }

}
