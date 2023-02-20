package io.cristos.petmanagement.utilities.mapper.pet;

import io.cristos.petmanagement.dtos.request.pet.PetRequestDto;
import io.cristos.petmanagement.dtos.response.pet.PetResponseDto;
import io.cristos.petmanagement.models.pet.Pet;

import java.util.Collection;
import java.util.List;

public interface PetMapper {

    Pet petRequestDtoToPet(PetRequestDto petRequestDto);

    Pet petRequestDtoToPet(Long petId, PetRequestDto petRequestDto);

    PetResponseDto petToPetResponseDto(Pet pet);

    List<PetResponseDto> petListToPetDtoList(Collection<Pet> petCollection);
}
