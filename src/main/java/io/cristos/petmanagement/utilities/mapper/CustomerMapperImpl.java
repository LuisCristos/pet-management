package io.cristos.petmanagement.utilities.mapper;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerRequest customerToCustomerRequest(Customer customer) {

        CustomerRequest customerRequest = new CustomerRequest();

        customerRequest.setId(customer.getId());
        customerRequest.setFirstName(customer.getFirstName());
        customerRequest.setLastName(customer.getLastName());
        customerRequest.setDateOfBirth(customer.getDateOfBirth());

        return customerRequest;
    }

    @Override
    public Customer customerRequestToCustomer(CustomerRequest customerRequest) {

        Customer customer = new Customer();

        customer.setId(customerRequest.getId());
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());

        return customer;
    }

    @Override
    public List<CustomerRequest> customerListToCustomerRequestList(Collection<Customer> customerCollection) {

        List<CustomerRequest> customerRequestsList = new ArrayList<>();

        for (Customer customer : customerCollection) {

            CustomerRequest customerRequest = customerToCustomerRequest(customer);

            customerRequestsList.add(customerRequest);
        }

        return customerRequestsList;
    }

}
