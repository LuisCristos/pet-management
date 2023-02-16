package io.cristos.petmanagement.utilities.mapper.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
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
    public CustomerDto customerToCustomerDto(Customer customer) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setDateOfBirth(customer.getBornAt());
        customerDto.setDateOfCreation(customer.getCreatedAt());
        customerDto.setGender(genderConverter.convertToDatabaseColumn(customer.getGender()));

        if (customer.getContact() != null) {
            customerDto.setContact(contactMapper.contactToContactDto(customer.getContact()));
        }

        return customerDto;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setId(customerDto.getCustomerId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBornAt(customerDto.getDateOfBirth());
        customer.setCreatedAt(customerDto.getDateOfCreation());
        customer.setGender(genderConverter.convertToEntityAttribute(customerDto.getGender()));

        if (customerDto.getContact() != null) {
            customer.setContact(contactMapper.contactDtoToContact(customerDto.getContact()));
        }

        return customer;
    }

    @Override
    public List<CustomerDto> customerListToCustomerDtoList(Collection<Customer> customerCollection) {

        List<CustomerDto> customercustomerDtosList = new ArrayList<>();

        for (Customer customer : customerCollection) {

            CustomerDto customerDto = customerToCustomerDto(customer);

            customercustomerDtosList.add(customerDto);
        }

        return customercustomerDtosList;
    }

}
