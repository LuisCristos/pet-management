package io.cristos.petmanagement.controllers.pet;

import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.services.pet.PetService;
import jakarta.validation.Valid;
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
@RequestMapping("/pets")
public class PetControllerImpl implements PetController {

    private final Logger logger = LoggerFactory.getLogger(PetControllerImpl.class);

    private final PetService petService;

    @Autowired
    public PetControllerImpl(PetService petService) {
        this.petService = petService;
    }

    @Override
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

    @Override
    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPets() {

        logger.info("getAllPets(). Retrieved all Pets.");

        return ResponseEntity.ok(petService.getAllPets());
    }

    @Override
    @GetMapping("/{petId}")
    public ResponseEntity<PetDto> findPetById(@PathVariable Long petId) {

        PetDto petDto = petService.findPetById(petId);

        return ResponseEntity.ok(petService.findPetById(petId));
    }

    @Override
    @DeleteMapping("/{petId}")
    public ResponseEntity<PetDto> deletePetById(@PathVariable Long petId) {

        petService.deletePetById(petId);

        logger.info("Deleted Pet with petId: " + petId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{petId}")
    public ResponseEntity<PetDto> updatePet(@PathVariable Long petId, @Valid @RequestBody PetDto petDto) {

        petService.updatePet(petId, petDto);

        logger.info("Updated Pet with petId: " + petId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
