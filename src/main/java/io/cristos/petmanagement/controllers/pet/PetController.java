package io.cristos.petmanagement.controllers.pet;

import io.cristos.petmanagement.dtos.pet.PetDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PetController {

    ResponseEntity<PetDto> savePet(PetDto petDto);

    ResponseEntity<List<PetDto>> getAllPets();

    ResponseEntity<PetDto> findPetById(Long petId);

    ResponseEntity<PetDto> deletePetById(Long petId);

    ResponseEntity<PetDto> updatePet(Long petId, PetDto petDto);

}
