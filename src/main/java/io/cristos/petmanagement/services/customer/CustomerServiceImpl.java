package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import io.cristos.petmanagement.utilities.mapper.customer.CustomerMapper;
import io.cristos.petmanagement.utilities.mapper.customer.CustomerMapperMS;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerMapperMS customerMapperMS;

    @Override
    public Customer saveCustomer(CustomerRequestDto customerRequestDto) {

        genderParameterCheck(customerRequestDto);

        return customerRepository.save(customerMapperMS.customerRequestDtoToCustomer(customerRequestDto));
    }

    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Page<CustomerResponseDto> getAllCustomersPageSortFilter(Pageable pageable, String searchValue) {

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

        genderParameterCheck(customerRequestDto);

        Customer updateCustomer = customerMapperMS.updateCustomerFromCustomerRequestDto(customerRequestDto, customer);

        return saveCustomer(updateCustomer);
    }

    @Override
    public Customer returnCustomerIfExists(Long customerId) {

        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    log.warn("{}, {}! An exception occurred!",
                            "An exception occurred!", "Customer with id: " + customerId + " cannot be found.",
                            new NotFoundException("Customer with id: " + customerId + " cannot be found."));

                    return new NotFoundException("Customer with id: " + customerId + " cannot be found.");
                }));

        return optionalCustomer.get();
    }

    private void genderParameterCheck(CustomerRequestDto customerRequestDto) {

        String gender = customerRequestDto.getGender();

        if (gender.equalsIgnoreCase("male") | gender.equalsIgnoreCase("female")
                | gender.equalsIgnoreCase("other")) {
            return;
        } else {
            log.warn("Customer {} was not saved. Because gender value {} is not supported.", customerRequestDto, gender);

            throw new IllegalArgumentException("Gender [" + gender + "] not supported. " +
                    "Allowed types are Male, Female, Other.");
        }
    }
}
