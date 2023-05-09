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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

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

//        return customerRepository.save(customerMapper.customerRequestDtoToCustomer(customerRequestDto));
        return customerRepository.save(customerMapperMS.customerRequestDtoToCustomer(customerRequestDto));
    }

    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Page<CustomerResponseDto> getAllCustomersPageSortFilter(int pageNumber, int pageSize, String direction,
                                                                   String orderBy, String searchValue, LocalDate birthdate) {

        PageRequest pageRequest = pagingSortingService.getPageRequest(pageNumber, pageSize, direction, orderBy);

        Page<Customer> customerPage = null;

        if (Objects.isNull(orderBy)) {

            customerPage = customerRepository.findAll(pageRequest);

        } else if (orderBy.equals("firstName")) {

            customerPage = customerRepository.findByFirstNameContainingIgnoreCase(searchValue, pageRequest);

        } else if (orderBy.equals("lastName")) {

            customerPage = customerRepository.findByLastNameContainingIgnoreCase(searchValue, pageRequest);

        } else if (birthdate.equals(2022 - 05 - 05)) {
            customerPage = customerRepository.findByBornAt(birthdate, pageRequest);
        }

        return customerMapper.pageCustomerToCustomerResponseDto(customerPage);
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

        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
        customer.setBornAt(customerRequestDto.getBornAt());

//        return customerRepository.save(customerMapper.customerRequestDtoToCustomer(customerId, customerRequestDto));
        return customerRepository.save(customer);
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
