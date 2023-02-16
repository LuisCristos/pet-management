package io.cristos.petmanagement.controllers.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
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
@RequestMapping("/v1/veterinarians")
public class VeterinarianController {

    private final Logger logger = LoggerFactory.getLogger(VeterinarianController.class);
    private final VeterinarianService veterinarianService;

    @Autowired
    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

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

    @GetMapping
    public ResponseEntity<List<VeterinarianDto>> getAllVeterinarians() {

        logger.info("Retrieved all veterinarians.");

        return ResponseEntity.ok(veterinarianService.getAllVeterinarians());
    }

    @GetMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianDto> findVeterinarianById(@PathVariable
                                                                @Positive
                                                                @NotNull Long veterinarianId) {

        logger.info("Retrieved veterinarian with veterinarianId: " + veterinarianId);

        return ResponseEntity.ok(veterinarianService.findVeterinarianById(veterinarianId));
    }

    @DeleteMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianDto> deleteVeterinarianById(@PathVariable
                                                                  @Positive
                                                                  @NotNull Long veterinarianId) {

        veterinarianService.deleteVeterinarianById(veterinarianId);

        logger.info("Deleted veterinarian with veterinarianId: " + veterinarianId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianDto> updateVeterinarianById(@PathVariable Long veterinarianId,
                                                                  @Valid
                                                                  @RequestBody VeterinarianDto veterinarianDto) {
        veterinarianService.updateVeterinarian(veterinarianId, veterinarianDto);

        logger.info("Updated veterinarian with veterinarianId: " + veterinarianId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
