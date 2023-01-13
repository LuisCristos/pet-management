package io.cristos.petmanagement.controllers.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import io.cristos.petmanagement.services.veterinarian.VeterinarianService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@RequestMapping("/veterinarians")
public class VeterinarianControllerImpl implements VeterinarianController {

    private final Logger logger = LoggerFactory.getLogger(VeterinarianControllerImpl.class);
    private final VeterinarianService veterinarianService;

    @Autowired
    public VeterinarianControllerImpl(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    @Override
    @PostMapping
    public ResponseEntity<VeterinarianDto> saveVeterinarian(@Valid @RequestBody VeterinarianDto veterinarianDto) {

        Veterinarian vet = veterinarianService.saveVeterinarian(veterinarianDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vet.getId())
                .toUri();

        logger.info(veterinarianDto + " saved to database.");

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<VeterinarianDto>> getAllVeterinarians() {

        logger.info("getAllVeterinarians(). Retrieved all veterinarians.");

        return ResponseEntity.ok(veterinarianService.getAllVeterinarians());
    }

    @Override
    @GetMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianDto> findVeterinarianById(@PathVariable
                                                                @Positive
                                                                @NotNull Long veterinarianId) {
        try {

            logger.info("Retrieved veterinarian with customerId: " + veterinarianId);

            return ResponseEntity.ok(veterinarianService.findVeterinarianById(veterinarianId));

        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "findVeterinarianById().", "veterinarian with veterinarianId: " + veterinarianId + " cannot be found because it does not exist.",
                    new NotFoundException("Veterinarian with veterinarianId: " + veterinarianId + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    @DeleteMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianDto> deleteVeterinarianById(@PathVariable
                                                                  @Positive
                                                                  @NotNull Long veterinarianId) {
        try {

            veterinarianService.deleteVeterinarianById(veterinarianId);

            logger.info("Deleted veterinarian with veterinarianId: " + veterinarianId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "deleteVeterinarianById().", "veterinarian with veterinarianId: " + veterinarianId + " cannot be deleted because it does not exist.",
                    new NotFoundException("Veterinarian with veterinarianId: " + veterinarianId + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianDto> updateVeterinarianById(@PathVariable Long veterinarianId,
                                                                  @Valid
                                                                  @RequestBody VeterinarianDto veterinarianDto) {
        try {

            veterinarianService.updateVeterinarian(veterinarianId, veterinarianDto);

            logger.info("Updated Veterinarian with customerID: " + veterinarianId);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "updateVeterinarianById().", "veterinarian with veterinarianId: " + veterinarianId + " cannot be updated because it does not exist.",
                    new NotFoundException("Veterinarian with veterinarianId: " + veterinarianId + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
