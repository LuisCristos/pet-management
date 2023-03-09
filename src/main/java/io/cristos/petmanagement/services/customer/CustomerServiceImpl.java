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
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 0;

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
    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers(String firstName, String lastName) {

        List<Customer> customerList = listCustomersBySearchedValue(firstName, lastName);

        if (customerList.isEmpty()) {

            logger.info("getAllCustomers(). Retrieved empty List.");

            return Collections.emptyList();
        }

        return customerMapper.customerListToCustomerResponseDtoList(customerList);
    }


    private List<Customer> listCustomersBySearchedValue(String firstName, String lastName) {

        if (StringUtils.hasText(firstName)) {

            return customerRepository.findByFirstNameContainingIgnoreCase(firstName);

        } else if (StringUtils.hasText(lastName)) {

            return customerRepository.findByLastNameContainingIgnoreCase(lastName);

        } else {

            return customerRepository.findAll();
        }
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
                            "An exception occurred!", "CustomerCsv with id: " + customerId + " cannot be found.",
                            new NotFoundException("CustomerCsv with id: " + customerId + " cannot be found."));

                    return new NotFoundException("CustomerCsv with id: " + customerId + " cannot be found.");
                }));

        return optionalCustomer.get();
    }
}
