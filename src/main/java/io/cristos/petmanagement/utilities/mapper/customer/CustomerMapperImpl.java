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

        customer.setFirstName(customerRequestDto.firstName());
        customer.setLastName(customerRequestDto.lastName());
        customer.setGender(genderConverter.convertToEntityAttribute(customerRequestDto.gender()));
        customer.setBornAt(customerRequestDto.bornAt());

        return customer;
    }

    @Override
    public Customer customerRequestDtoToCustomer(Long customerId, CustomerRequestDto customerRequestDto) {

        Customer customer = new Customer();

        customer.setId(customerId);
        customer.setFirstName(customerRequestDto.firstName());
        customer.setLastName(customerRequestDto.lastName());
        customer.setGender(genderConverter.convertToEntityAttribute(customerRequestDto.gender()));
        customer.setBornAt(customerRequestDto.bornAt());

        return customer;
    }

    @Override
    public CustomerResponseDto customerToCustomerResponseDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBornAt(),
                genderConverter.convertToDatabaseColumn(customer.getGender()),
                customer.getBornAt(),
                customer.getAge()
        );
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
