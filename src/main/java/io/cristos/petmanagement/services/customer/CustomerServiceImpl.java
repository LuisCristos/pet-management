package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import io.cristos.petmanagement.utilities.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer saveCustomer(CustomerRequest customerRequest) {

        boolean isNull = Objects.isNull(customerRequest);

        if (isNull) {
            throw new RuntimeException("CustomerRequest is null" + customerRequest.toString());
        }

        logger.info(customerRequest.toString() + " saved in database");

        return customerRepository.save(customerMapper.customerRequestToCustomer(customerRequest));
    }

    @Override
    public List<Customer> getAllCustomers() {

        Collection<Customer> customerCollection = customerRepository.findAll();

        if (customerCollection.isEmpty()) {
            return Collections.emptyList();
        }

        List<Customer> customerList = new ArrayList<>(customerCollection);

        return customerList;
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) throws NotFoundException {

        Optional<Customer> optionalPerson = customerRepository.findById(id);

        Customer customer = optionalPerson.orElseThrow(
                () -> new NotFoundException("Customer with id: " + id + " not found."));

        return customerRepository.findById(id);
    }

    @Override
    public void deleteCustomerById(Long id) throws NotFoundException {

        boolean exists = customerRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Customer with id: " + id + " not found.");
        }

        customerRepository.deleteById(id);

    }

}
