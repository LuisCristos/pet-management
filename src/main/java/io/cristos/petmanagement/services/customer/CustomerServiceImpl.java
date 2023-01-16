package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import io.cristos.petmanagement.utilities.mapper.customer.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Customer saveCustomer(CustomerDto customerDto) {

        logger.info(customerDto + "saved in database");

        return customerRepository.save(customerMapper.customerDtoToCustomer(customerDto));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        Collection<Customer> customerCollection = customerRepository.findAll();

        if (customerCollection.isEmpty()) {

            logger.info("getAllCustomers(). Retrieved empty List.");
            return Collections.emptyList();
        }

        logger.info("getAllCustomers(). Retrieved all customers.");

        return customerMapper.customerListToCustomerDtoList(customerCollection);
    }

    @Override
    public CustomerDto findCustomerById(Long id) throws NotFoundException {

        Optional<Customer> optionalPerson = customerRepository.findById(id);

        if (optionalPerson.isEmpty()) {

            logger.warn("{}, {}! An exception occurred!",
                    "findCustomerById().", "Customer with id: " + id + " cannot be found because it does not exist.",
                    new NotFoundException("Customer with id: " + id + " not found"));

            throw new NotFoundException("Customer with id: " + id + " cannot be found.");
        }

        logger.info("Retrieved customer with id: " + id);

        return customerMapper.customerToCustomerDto(optionalPerson.get());
    }

    @Override
    public void deleteCustomerById(Long id) throws NotFoundException {

        boolean exists = customerRepository.existsById(id);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "deleteCustomerById().", "Customer with id: " + id + " cannot be deleted because it does not exist.",
                    new NotFoundException("Customer with id: " + id + " not found"));

            throw new NotFoundException("Customer ID: " + id + " cannot be deleted because it does not exist.");
        }

        logger.info("Deleted customer with id: " + id);

        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDto customerDto) throws NotFoundException {

        boolean exists = customerRepository.existsById(id);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "updateCustomer().", "Customer with id: " + id + " cannot be updated because it does not exist.",
                    new NotFoundException("Customer with id: " + id + " not found"));

            throw new NotFoundException("Customer ID: " + id + " cannot be updated because it does not exist.");

        }

        logger.info("Updated customer with id: " + id);

        return customerRepository.save(
                customerMapper.customerDtoToCustomer(customerDto));
    }
}
