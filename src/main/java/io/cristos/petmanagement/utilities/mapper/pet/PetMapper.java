package io.cristos.petmanagement.utilities.mapper.pet;

import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.pet.Pet;

import java.util.Collection;
import java.util.List;

public interface PetMapper {

    PetDto petToPetDto(Pet pet);

    Pet petDtoToPet(PetDto petDto);

    List<PetDto> petListToPetDtoList(Collection<Pet> petCollection);
}
