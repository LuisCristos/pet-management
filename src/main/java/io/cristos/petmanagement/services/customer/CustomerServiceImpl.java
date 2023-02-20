package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
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
    public Customer saveCustomer(CustomerRequestDto customerRequestDto) {

        return customerRepository.save(customerMapper.customerRequestDtoToCustomer(customerRequestDto));
    }

    @Override
    public Customer saveCustomerWithContact(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        Collection<Customer> customerCollection = customerRepository.findAll();

        if (customerCollection.isEmpty()) {
            logger.info("getAllCustomers(). Retrieved empty List.");
            return Collections.emptyList();
        }

        return customerMapper.customerListToCustomerResponseDtoList(customerCollection);
    }

    @Override
    public CustomerResponseDto findCustomerById(Long customerId) {

        Customer customer = returnCustomerIfExists(customerId);

        return customerMapper.customerToCustomerResponseDto(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {

        returnCustomerIfExists(customerId);

        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomer(Long customerId, CustomerRequestDto customerRequestDto) {

        returnCustomerIfExists(customerId);

        return customerRepository.save(customerMapper.customerRequestDtoToCustomer(customerId, customerRequestDto));
    }

    @Override
    public Customer returnCustomerIfExists(Long customerId) {

        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "An exception occurred!", "Customer with id: " + customerId + " cannot be found.",
                            new NotFoundException("Customer with id: " + customerId + " cannot be found."));

                    return new NotFoundException("Customer with id: " + customerId + " cannot be found.");
                }));

        return optionalCustomer.get();
    }
}
