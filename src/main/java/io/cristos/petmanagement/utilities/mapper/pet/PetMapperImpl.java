package io.cristos.petmanagement.utilities.mapper.pet;

import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.pet.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PetMapperImpl implements PetMapper {
    @Override
    public PetDto petToPetDto(Pet pet) {

        PetDto petDto = new PetDto();

        petDto.setId(pet.getId());
        petDto.setName(pet.getName());
        petDto.setGender(pet.getGender());
        petDto.setDateOfBirth(pet.getDateOfBirth());
        petDto.setDateOfCreation(pet.getDateOfCreation());

        return petDto;
    }

    @Override
    public Pet petDtoToPet(PetDto petDto) {

        Pet pet = new Pet();

        pet.setId(petDto.getId());
        pet.setName(petDto.getName());
        pet.setGender(petDto.getGender());
        pet.setDateOfBirth(petDto.getDateOfBirth());
        pet.setDateOfCreation(petDto.getDateOfCreation());

        return pet;
    }

    @Override
    public List<PetDto> petListToPetDtoList(Collection<Pet> petCollection) {

        List<PetDto> petToPetDtoList = new ArrayList<>();

        for (Pet pet : petCollection) {

            PetDto petDto = petToPetDto(pet);

            petToPetDtoList.add(petDto);
        }

        return petToPetDtoList;
    }
}
