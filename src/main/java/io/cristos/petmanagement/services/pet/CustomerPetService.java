package io.cristos.petmanagement.services.pet;

import io.cristos.petmanagement.dtos.request.pet.PetRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerPetResponseDto;
import io.cristos.petmanagement.models.customer.Customer;


public interface CustomerPetService {

    Customer savaCustomerPetByCustomerId(Long customerId, PetRequestDto petRequestDto);

    CustomerPetResponseDto findCustomerPetByCustomerId(Long customerId, Long petId);

    CustomerPetResponseDto getCustomerWithAllPets(Long customerId);

    Customer updateCustomerPetByCustomerId(Long customerId, PetRequestDto petRequestDto,
                                           Long petId);

    Customer returnCustomerIfExists(Long customerId);
}
