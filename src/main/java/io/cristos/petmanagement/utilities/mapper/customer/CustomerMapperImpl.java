package io.cristos.petmanagement.utilities.mapper.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.utilities.mapper.contact.ContactMapper;
import io.cristos.petmanagement.utilities.mapper.genderconverter.GenderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    private final GenderConverter genderConverter;
    private final ContactMapper contactMapper;

    @Autowired
    public CustomerMapperImpl(GenderConverter genderConverter, ContactMapper contactMapper) {
        this.genderConverter = genderConverter;
        this.contactMapper = contactMapper;
    }

    @Override
    public Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = new Customer();

        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
        customer.setGender(genderConverter.convertToEntityAttribute(customerRequestDto.getGender()));
        customer.setBornAt(customerRequestDto.getBornAt());

        return customer;
    }

    @Override
    public Customer customerRequestDtoToCustomer(Long customerId, CustomerRequestDto customerRequestDto) {

        Customer customer = new Customer();

        customer.setId(customerId);
        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
        customer.setGender(genderConverter.convertToEntityAttribute(customerRequestDto.getGender()));
        customer.setBornAt(customerRequestDto.getBornAt());

        return customer;
    }

    @Override
    public CustomerResponseDto customerToCustomerResponseDto(Customer customer) {

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();

        customerResponseDto.setCustomerId(customer.getId());
        customerResponseDto.setFirstName(customer.getFirstName());
        customerResponseDto.setLastName(customer.getLastName());
        customerResponseDto.setBornAt(customer.getBornAt());
        customerResponseDto.setGender(genderConverter.convertToDatabaseColumn(customer.getGender()));
        customerResponseDto.setCreatedAt(customer.getCreatedAt());
        customerResponseDto.setAge(customer.getAge());

        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> customerListToCustomerResponseDtoList(Collection<Customer> customerCollection) {

        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();

        for (Customer customer : customerCollection) {

            CustomerResponseDto customerResponseDto = customerToCustomerResponseDto(customer);

            customerResponseDtoList.add(customerResponseDto);
        }

        return customerResponseDtoList;
    }
}
