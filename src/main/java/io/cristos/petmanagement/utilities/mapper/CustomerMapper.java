package io.cristos.petmanagement.utilities.mapper;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.models.customer.Customer;

public interface CustomerMapper {

    CustomerRequest customerToCustomerRequest(Customer customer);

    Customer customerRequestToCustomer(CustomerRequest customerRequest);
}