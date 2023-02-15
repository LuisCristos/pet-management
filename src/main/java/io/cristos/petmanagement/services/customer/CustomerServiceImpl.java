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

        return customerRepository.save(customerMapper.customerDtoToCustomer(customerDto));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        Collection<Customer> customerCollection = customerRepository.findAll();

        if (customerCollection.isEmpty()) {

            logger.info("getAllCustomers(). Retrieved empty List.");
            return Collections.emptyList();
        }

        return customerMapper.customerListToCustomerDtoList(customerCollection);
    }

    @Override
    public CustomerDto findCustomerById(Long customerId) {

        final String action = "found";
        Customer customer = returnCustomerIfExists(customerId, action);

        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {

        final String action = "found";
        returnCustomerIfExists(customerId, action);

        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomer(Long customerId, CustomerDto customerDto) {

        final String action = "found";
        returnCustomerIfExists(customerId, action);

        return customerRepository.save(customerMapper.customerDtoToCustomer(customerDto));
    }

    @Override
    public Customer returnCustomerIfExists(Long customerId, String action) {

        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findCustomerById().", "Customer with id: " + customerId + " cannot be " + action + " because it does not exist.",
                            new NotFoundException("Customer with id: " + customerId + " cannot be " + action + " because it does not exist."));

                    return new NotFoundException("Customer with id: " + customerId + " cannot be " + action + " because it does not exist.");
                }));

        return optionalCustomer.get();
    }
}
