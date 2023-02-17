package io.cristos.petmanagement.services.veterinarian;

import io.cristos.petmanagement.dtos.request.veterinarian.VeterinarianRequestDto;
import io.cristos.petmanagement.dtos.response.veterinarian.VeterinarianResponseDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

import java.util.List;

public interface VeterinarianService {

    Veterinarian saveVeterinarian(VeterinarianRequestDto veterinarianRequestDto);

    Veterinarian saveVeterinarianContact(Veterinarian veterinarian);

    List<VeterinarianResponseDto> getAllVeterinarians();

    VeterinarianResponseDto findVeterinarianById(Long veterinarianId);

    void deleteVeterinarianById(Long veterinarianId);

    Veterinarian updateVeterinarian(Long veterinarianId, VeterinarianRequestDto veterinarianRequestDto);

    Veterinarian returnVeterinarianIfExists(Long veterinarianId, String action);

    Veterinarian returnVeterinarianIfExists(Long veterinarianId);
}
