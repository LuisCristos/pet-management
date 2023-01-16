package io.cristos.petmanagement.utilities.mapper.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

import java.util.Collection;
import java.util.List;

public interface VeterinarianMapper {

    VeterinarianDto veterinarianToVeterinarianDto(Veterinarian veterinarian);

    Veterinarian veterinarianDtoToVeterinarian(VeterinarianDto veterinarianDto);

    List<VeterinarianDto> veterinarianListToVeterinarianDtoList(Collection<Veterinarian> veterinarianCollection);

}
