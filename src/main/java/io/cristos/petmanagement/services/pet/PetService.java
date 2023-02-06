package io.cristos.petmanagement.services.pet;


import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.pet.Pet;

import java.util.List;

public interface PetService {

    Pet savePet(PetDto petDto);

    List<PetDto> getAllPets();

    PetDto findPetById(Long id);

    void deletePetById(Long id);

    Pet updatePetById(Long id, PetDto petDto);

}
