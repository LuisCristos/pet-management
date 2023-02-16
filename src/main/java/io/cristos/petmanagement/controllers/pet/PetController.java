package io.cristos.petmanagement.controllers.pet;

import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.services.pet.PetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    public ResponseEntity<PetDto> savePet(@Valid @RequestBody PetDto petDto) {

        Pet pet = petService.savePet(petDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{petId}")
                .buildAndExpand(pet.getId())
                .toUri();

        logger.info(petDto + " saved to database");

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPets() {

        logger.info("getAllPets(). Retrieved all Pets.");

        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDto> findPetById(@PathVariable(name = "petId")
                                              @NotNull
                                              @Min(1) Long petId) {

        logger.info("Find Pet with petId: " + petId);

        return ResponseEntity.ok(petService.findPetById(petId));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<PetDto> deletePetById(@PathVariable(name = "petId")
                                                @NotNull
                                                @Min(1) Long petId) {

        petService.deletePetById(petId);

        logger.info("Deleted Pet with petId: " + petId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetDto> updatePetById(@PathVariable(name = "petId")
                                                @NotNull
                                                @Min(1) Long petId,
                                                @Valid
                                                @RequestBody PetDto petDto) {

        petService.updatePetById(petId, petDto);

        logger.info("Updated Pet with petId: " + petId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
