package io.cristos.petmanagement.services.pet;

import io.cristos.petmanagement.dtos.request.pet.PetRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerPetResponseDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import io.cristos.petmanagement.repositories.pet.PetRepository;
import io.cristos.petmanagement.utilities.mapper.customer.CustomerMapper;
import io.cristos.petmanagement.utilities.mapper.pet.PetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerPetServiceImpl implements CustomerPetService {

    private final Logger logger = LoggerFactory.getLogger(CustomerPetServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PetMapper petMapper;
    private final PetRepository petRepository;

    @Autowired
    public CustomerPetServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
                                  PetMapper petMapper, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.petMapper = petMapper;
        this.petRepository = petRepository;
    }

    @Override
    public Customer savaCustomerPetByCustomerId(Long customerId, PetRequestDto petRequestDto) {

        Customer customer = returnCustomerIfExists(customerId);

        Pet pet = petMapper.petRequestDtoToPet(petRequestDto);

        customer.addPet(pet);
        pet.setCustomer(customer);

        return customerRepository.save(customer);
    }

    @Override
    public CustomerPetResponseDto findCustomerPetByCustomerId(Long customerId, Long petId) {

        Customer customer = returnCustomerIfExists(customerId);

        List<Pet> petList = new ArrayList<>(customer.getPets());

        for (Pet pet : petList) {

            if (pet.getId() == petId) {

                customer.getPets().clear();

                customer.addPet(pet);

                return customerMapper.customerWithPetsToCustomerWithPetsDto(customer);

            } else {

                customer.getPets().clear();
            }
        }

        return customerMapper.customerWithPetsToCustomerWithPetsDto(customer);
    }

    @Override
    public CustomerPetResponseDto getCustomerWithAllPets(Long customerId) {

        Customer customer = returnCustomerIfExists(customerId);

        return customerMapper.customerWithPetsToCustomerWithPetsDto(customer);
    }

    @Override
    public Customer updateCustomerPetByCustomerId(Long customerId,
                                                  PetRequestDto petRequestDto, Long petId) {

        Customer customer = returnCustomerIfExists(customerId);

        Pet pet = petMapper.petRequestDtoToPet(petId, petRequestDto);

        customer.addPet(pet);
        pet.setCustomer(customer);

        return customerRepository.save(customer);
    }

    @Override
    public Customer returnCustomerIfExists(Long customerId) {

        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(customerId))
                .orElseThrow(
                        () -> {
                            logger.warn(
                                    "An exception occurred!", "CustomerCsv with id: " + customerId + " cannot be found.",
                                    new NotFoundException("CustomerCsv with id: " + customerId + " cannot be found."));

                            throw new NotFoundException("CustomerCsv with id: " + customerId + " cannot be found.");
                        }
                );

        return optionalCustomer.get();
    }
}
