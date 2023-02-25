package io.cristos.petmanagement.services.customer;

import io.cristos.petmanagement.dtos.response.customer.CustomerPetResponseDto;


public interface CustomerPetService {

    CustomerPetResponseDto getCustomerWithAllPets(Long customerId);
}
