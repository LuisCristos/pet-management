package io.cristos.petmanagement.utilities.mapper;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerRequest customerToCustomerRequest(Customer customer) {

        CustomerRequest customerRequest = new CustomerRequest();

        customerRequest.setFirstName(customer.getFirstName());
        customerRequest.setLastName(customer.getLastName());
        customerRequest.setDateOfBirth(customer.getDateOfBirth());

        return customerRequest;
    }

    @Override
    public Customer customerRequestToCustomer(CustomerRequest customerRequest) {

        Customer customer = new Customer();

        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());

        return customer;
    }
}
