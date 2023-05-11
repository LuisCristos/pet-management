package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import io.cristos.petmanagement.utilities.mapper.customer.CustomerMapper;
import io.cristos.petmanagement.utilities.mapper.customer.CustomerMapperMS;
import io.cristos.petmanagement.utilities.userinput.pagingandsorting.PagingSortingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerMapperMS customerMapperMS;
    private final PagingSortingService pagingSortingService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
                               CustomerMapperMS customerMapperMS, PagingSortingService pagingSortingService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerMapperMS = customerMapperMS;
        this.pagingSortingService = pagingSortingService;
    }

    @Override
    public Customer saveCustomer(CustomerRequestDto customerRequestDto) {

        return customerRepository.save(customerMapperMS.customerRequestDtoToCustomer(customerRequestDto));
    }

    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Page<CustomerResponseDto> getAllCustomersPageSortFilter(Pageable pageable, String searchValue, LocalDate birthdate) {

        Sort sort = pageable.getSort();
        String orderProperty = null;

        for (Sort.Order order : sort) {
            orderProperty = order.getProperty();
        }

        if (StringUtils.hasText(searchValue) && StringUtils.hasText(orderProperty) && orderProperty.equals("firstName")) {

            Page<Customer> customersPage = customerRepository.findByFirstNameContainingIgnoreCase(searchValue, pageable);

            var customersResponseDtoList = customersPage.getContent().stream()
                    .map(customerMapperMS::customerListToCustomerResponseDtoList)
                    .collect(Collectors.toList());

            return new PageImpl<>(customersResponseDtoList);

        } else if (StringUtils.hasText(searchValue) && StringUtils.hasText(orderProperty) && orderProperty.equals("lastName")) {

            Page<Customer> customersPage = customerRepository.findByLastNameContainingIgnoreCase(searchValue, pageable);

            var customersResponseDtoList = customersPage.getContent().stream()
                    .map(customerMapperMS::customerListToCustomerResponseDtoList)
                    .collect(Collectors.toList());

            return new PageImpl<>(customersResponseDtoList);

        } else {

            Page<Customer> findCustomers = customerRepository.findAll(pageable);

            var customerResponseDtoList = findCustomers.getContent().stream()
                    .map(customerMapperMS::customerListToCustomerResponseDtoList)
                    .collect(Collectors.toList());

            return new PageImpl<>(customerResponseDtoList);
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

        Customer customer = returnCustomerIfExists(customerId);

        Customer updateCustomer = customerMapperMS.updateCustomerFromCustomerRequestDto(customerRequestDto, customer);

        return saveCustomer(updateCustomer);
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
