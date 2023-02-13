package io.cristos.petmanagement.services.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

import java.util.List;

public interface VeterinarianService {

    Veterinarian saveVeterinarian(VeterinarianDto veterinarianDto);

    List<VeterinarianDto> getAllVeterinarians();

    VeterinarianDto findVeterinarianById(Long id);

    void deleteVeterinarianById(Long id);

    Veterinarian updateVeterinarian(Long id, VeterinarianDto veterinarianDto);
}
