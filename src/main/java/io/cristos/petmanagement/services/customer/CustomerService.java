package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;

import java.util.List;

public interface CustomerService {

    Customer savePerson(CustomerRequest customerRequest);

    List<Customer> getAllPersons();

    Customer findById(Long id) throws NotFoundException;
}
