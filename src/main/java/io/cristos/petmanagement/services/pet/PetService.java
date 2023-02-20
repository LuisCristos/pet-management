package io.cristos.petmanagement.services.pet;


import io.cristos.petmanagement.dtos.request.pet.PetRequestDto;
import io.cristos.petmanagement.dtos.response.pet.PetResponseDto;
import io.cristos.petmanagement.models.pet.Pet;

import java.util.List;

public interface PetService {

    Pet savePet(PetRequestDto petRequestDto);

    List<PetResponseDto> getAllPets();

    PetResponseDto findPetById(Long petId);

    void deletePetById(Long petId);

    Pet updatePetById(Long petId, PetRequestDto petRequestDto);

    Pet returnPetIfExists(Long petId);
}
