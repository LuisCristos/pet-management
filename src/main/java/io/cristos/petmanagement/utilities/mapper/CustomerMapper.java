package io.cristos.petmanagement.utilities.mapper;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.models.customer.Customer;

import java.util.Collection;
import java.util.List;

public interface CustomerMapper {

    CustomerRequest customerToCustomerRequest(Customer customer);

    Customer customerRequestToCustomer(CustomerRequest customerRequest);

    List<CustomerRequest> customerListToCustomerRequestList(Collection<Customer> customerCollection);

}
