package io.cristos.petmanagement.controllers.veterinarian;

import io.cristos.petmanagement.dtos.request.veterinarian.VeterinarianRequestDto;
import io.cristos.petmanagement.dtos.response.veterinarian.VeterinarianResponseDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import io.cristos.petmanagement.services.veterinarian.VeterinarianService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/veterinarians")
@Validated
public class VeterinarianController {

    private final Logger logger = LoggerFactory.getLogger(VeterinarianController.class);
    private final VeterinarianService veterinarianService;

    @Autowired
    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    @PostMapping
    public ResponseEntity<VeterinarianResponseDto> saveVeterinarian(@Valid
                                                                    @RequestBody VeterinarianRequestDto veterinarianRequestDto) {

        Veterinarian vet = veterinarianService.saveVeterinarian(veterinarianRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vet.getId())
                .toUri();

        logger.info(veterinarianRequestDto + " saved to database.");

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianResponseDto>> getAllVeterinarians() {

        logger.info("Retrieved all veterinarians.");

        return ResponseEntity.ok(veterinarianService.getAllVeterinarians());
    }

    @GetMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianResponseDto> findVeterinarianById(@PathVariable
                                                                        @Positive
                                                                        @NotNull
                                                                        @Min(value = 1, message = "Must be greater than or equal to 1")
                                                                        Long veterinarianId) {

        logger.info("Retrieved veterinarian with veterinarianId: " + veterinarianId);

        return ResponseEntity.ok(veterinarianService.findVeterinarianById(veterinarianId));
    }

    @PutMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianResponseDto> updateVeterinarianById(@PathVariable
                                                                          @NotNull
                                                                          @Min(value = 1, message = "Must be greater than or equal to 1")
                                                                          Long veterinarianId,
                                                                          @Valid
                                                                          @RequestBody VeterinarianRequestDto veterinarianRequestDto) {

        veterinarianService.updateVeterinarian(veterinarianId, veterinarianRequestDto);

        logger.info("Updated veterinarian with veterinarianId: " + veterinarianId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianResponseDto> deleteVeterinarianById(@PathVariable
                                                                          @Positive
                                                                          @NotNull
                                                                          @Min(value = 1, message = "Must be greater than or equal to 1")
                                                                          Long veterinarianId) {

        veterinarianService.deleteVeterinarianById(veterinarianId);

        logger.info("Deleted veterinarian with veterinarianId: " + veterinarianId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
