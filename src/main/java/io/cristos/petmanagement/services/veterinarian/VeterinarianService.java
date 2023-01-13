package io.cristos.petmanagement.services.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

import java.util.List;

public interface VeterinarianService {

    Veterinarian saveVeterinarian(VeterinarianDto veterinarianDto);

    List<VeterinarianDto> getAllVeterinarians();

    VeterinarianDto findVeterinarianById(Long id) throws NotFoundException;

    void deleteVeterinarianById(Long id) throws NotFoundException;

    Veterinarian updateVeterinarian(Long id, VeterinarianDto veterinarianDto) throws NotFoundException;
}
