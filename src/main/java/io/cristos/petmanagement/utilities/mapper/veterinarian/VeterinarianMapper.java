package io.cristos.petmanagement.utilities.mapper.veterinarian;

import io.cristos.petmanagement.dtos.request.veterinarian.VeterinarianRequestDto;
import io.cristos.petmanagement.dtos.response.veterinarian.VeterinarianResponseDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

import java.util.Collection;
import java.util.List;

public interface VeterinarianMapper {

    Veterinarian veterinarianRequestDtoToVeterinarian(VeterinarianRequestDto veterinarianRequestDto);

    Veterinarian veterinarianRequestDtoToVeterinarian(Long veterinarianId, VeterinarianRequestDto veterinarianRequestDto);

    VeterinarianResponseDto veterinarianToVeterinarianResponseDto(Veterinarian veterinarian);

    List<VeterinarianResponseDto> veterinarianListToVeterinarianResponseDtoList(Collection<Veterinarian> veterinarianCollection);


}
