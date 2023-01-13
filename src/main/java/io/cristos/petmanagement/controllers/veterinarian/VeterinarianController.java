package io.cristos.petmanagement.controllers.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VeterinarianController {

    ResponseEntity<VeterinarianDto> saveVeterinarian(VeterinarianDto veterinarianDto);

    ResponseEntity<List<VeterinarianDto>> getAllVeterinarians();

    ResponseEntity<VeterinarianDto> findVeterinarianById(Long veterinarianId);

    ResponseEntity<VeterinarianDto> deleteVeterinarianById(Long veterinarianId);

    ResponseEntity<VeterinarianDto> updateVeterinarianById(Long veterinarianId, VeterinarianDto veterinarianDto);
}
