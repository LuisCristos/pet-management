package io.cristos.petmanagement.utilities.mapper.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerPetResponseDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.utilities.genderconverter.GenderConverter;
import io.cristos.petmanagement.utilities.mapper.contact.ContactMapper;
import io.cristos.petmanagement.utilities.mapper.pet.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    private final GenderConverter genderConverter;
    private final ContactMapper contactMapper;
    private final PetMapper petMapper;

    @Autowired
    public CustomerMapperImpl(GenderConverter genderConverter, ContactMapper contactMapper, PetMapper petMapper) {
        this.genderConverter = genderConverter;
        this.contactMapper = contactMapper;
        this.petMapper = petMapper;
    }

    @Override
    public Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = createCustomerFromCustomerRequestDto(customerRequestDto);

        return customer;
    }

    @Override
    public Customer customerRequestDtoToCustomer(Long customerId, CustomerRequestDto customerRequestDto) {

        Customer customer = createCustomerFromCustomerRequestDto(customerRequestDto);

        customer.setId(customerId);

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

    @Override
    public Customer createCustomerFromCustomerRequestDto(CustomerRequestDto customerRequestDto) {

        Customer customer = new Customer();

        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
        customer.setGender(genderConverter.convertToEntityAttribute(customerRequestDto.getGender()));
        customer.setBornAt(customerRequestDto.getBornAt());

        return customer;
    }

    @Override
    public CustomerPetResponseDto customerWithPetsToCustomerWithPetsDto(Customer customer) {

        CustomerPetResponseDto customerPetResponseDto = new CustomerPetResponseDto();

        customerPetResponseDto.setCustomerId(customer.getId());
        customerPetResponseDto.setFirstName(customer.getFirstName());
        customerPetResponseDto.setLastName(customer.getLastName());
        customerPetResponseDto.setBornAt(customer.getBornAt());
        customerPetResponseDto.setGender(genderConverter.convertToDatabaseColumn(customer.getGender()));
        customerPetResponseDto.setCreatedAt(customer.getCreatedAt());
        customerPetResponseDto.setAge(customer.getAge());

        if (Objects.nonNull(customer.getPets())) {

            for (Pet pet : customer.getPets()) {

                customerPetResponseDto.addPets(petMapper.petToPetResponseDto(pet));
            }
        }

        return customerPetResponseDto;
    }
}
