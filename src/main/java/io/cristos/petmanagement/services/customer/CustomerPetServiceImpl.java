package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.response.customer.CustomerPetResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import io.cristos.petmanagement.utilities.mapper.customer.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerPetServiceImpl implements CustomerPetService {

    private final Logger logger = LoggerFactory.getLogger(CustomerPetServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerPetServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerPetResponseDto getCustomerWithAllPets(Long customerId) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        return customerMapper.customerWithPetsToCustomerWithPetsDto(optionalCustomer.get());
    }
}
