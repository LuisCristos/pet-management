package io.cristos.petmanagement.controllers.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VeterinarianController {

    ResponseEntity<VeterinarianDto> saveVeterinarian(@Valid
                                                     @RequestBody VeterinarianDto veterinarianDto);

    ResponseEntity<List<VeterinarianDto>> getAllVeterinarians();

    ResponseEntity<VeterinarianDto> findVeterinarianById(@PathVariable(name = "veterinarianId")
                                                         @Min(1)
                                                         @NotNull Long veterinarianId);

    ResponseEntity<VeterinarianDto> deleteVeterinarianById(@PathVariable(name = "veterinarianId")
                                                           @Min(1)
                                                           @NotNull Long veterinarianId);

    ResponseEntity<VeterinarianDto> updateVeterinarianById(@PathVariable(name = "veterinarianId")
                                                           @Min(1)
                                                           @NotNull Long veterinarianId,
                                                           @Valid
                                                           @RequestBody VeterinarianDto veterinarianDto);
}