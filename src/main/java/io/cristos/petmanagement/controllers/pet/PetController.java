package io.cristos.petmanagement.controllers.pet;


import io.cristos.petmanagement.dtos.request.pet.PetRequestDto;
import io.cristos.petmanagement.dtos.response.pet.PetResponseDto;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.services.pet.PetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/pets")
public class PetController {

    private final Logger logger = LoggerFactory.getLogger(PetController.class);
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetResponseDto> savePet(@Valid @RequestBody PetRequestDto petRequestDto) {

        Pet pet = petService.savePet(petRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{petId}")
                .buildAndExpand(pet.getId())
                .toUri();

        logger.info(petRequestDto + " saved to database");

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDto>> getAllPets() {

        logger.info("getAllPets(). Retrieved all Pets.");

        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResponseDto> findPetById(@PathVariable(name = "petId")
                                                      @Min(value = 1, message = "{validation.min.pathvariable}")
                                                      Long petId) {

        logger.info("Find Pet with petId: " + petId);

        return ResponseEntity.ok(petService.findPetById(petId));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<PetResponseDto> deletePetById(@PathVariable(name = "petId")
                                                        @Min(value = 1, message = "{validation.min.pathvariable}")
                                                        Long petId) {

        petService.deletePetById(petId);

        logger.info("Deleted Pet with petId: " + petId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetResponseDto> updatePetById(@PathVariable(name = "petId")
                                                            @Min(value = 1, message = "{validation.min.pathvariable}")
                                                            Long petId,
                                                        @Valid
                                                            @RequestBody PetRequestDto petRequestDto) {

        petService.updatePetById(petId, petRequestDto);

        logger.info("Updated Pet with petId: " + petId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
