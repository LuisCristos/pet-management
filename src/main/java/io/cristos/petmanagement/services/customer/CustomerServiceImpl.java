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

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
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
            throw new RuntimeException("CustomerRequest is null");
        }

        logger.info(customerRequest + "saved in database");

        return customerRepository.save(customerMapper.customerRequestToCustomer(customerRequest));
    }

    @Override
    public List<CustomerRequest> getAllCustomers() {

        Collection<Customer> customerCollection = customerRepository.findAll();

        if (customerCollection.isEmpty()) {
            return Collections.emptyList();
        }

        logger.info("Retrieved customer List");

        return customerMapper.customerListToCustomerRequestList(customerCollection);
    }

    @Override
    public CustomerRequest findCustomerById(Long id) throws NotFoundException {

        Optional<Customer> optionalPerson = customerRepository.findById(id);

        if (optionalPerson.isEmpty()) {

            logger.info("findCustomerById(). Customer with id: " + id + " cannot be found.");

            throw new NotFoundException("Customer with id: " + id + " cannot be found.");
        }

        logger.info("Retrieved customer with id: " + id);

        return customerMapper.customerToCustomerRequest(optionalPerson.get());
    }

    @Override
    public void deleteCustomerById(Long id) throws NotFoundException {

        boolean exists = customerRepository.existsById(id);

        if (!exists) {

            logger.info("deleteCustomerById(). Customer ID: " + id + " cannot be deleted because it does not exist.");

            throw new NotFoundException("Customer ID: " + id + " cannot be deleted because it does not exist.");
        }

        logger.info("Deleted customer with id: " + id);

        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Long id, CustomerRequest customerRequest) throws NotFoundException {

        boolean exists = customerRepository.existsById(id);

        if (!exists) {

            logger.info("updateCustomer(). Customer ID:  " + id + " cannot be updated because it does not exist.");

            throw new NotFoundException("Customer ID: " + id + " cannot be updated because it does not exist.");

        }

        logger.info("Updated customer with id: " + id);

        return customerRepository.save(
                customerMapper.customerRequestToCustomer(customerRequest));
    }
}
