package io.cristos.petmanagement.controllers.pet;

import io.cristos.petmanagement.dtos.pet.PetDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PetController {

    ResponseEntity<PetDto> savePet(@Valid @RequestBody PetDto petDto);

    ResponseEntity<List<PetDto>> getAllPets();

    ResponseEntity<PetDto> findPetById(@PathVariable(name = "petId")
                                       @NotNull
                                       @Min(1) Long petId);

    ResponseEntity<PetDto> deletePetById(@PathVariable(name = "petId")
                                         @NotNull
                                         @Min(1) Long petId);

    ResponseEntity<PetDto> updatePetById(@PathVariable(name = "petId")
                                         @NotNull
                                         @Min(1) Long petId,
                                         @Valid
                                         @RequestBody PetDto petDto);

}
