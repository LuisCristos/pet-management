package io.cristos.petmanagement.utilities.mapper.pet;

import io.cristos.petmanagement.dtos.request.pet.PetRequestDto;
import io.cristos.petmanagement.dtos.response.pet.PetResponseDto;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.utilities.mapper.diagnosis.DiagnosisMapper;
import io.cristos.petmanagement.utilities.mapper.genderconverter.GenderConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PetMapperImpl implements PetMapper {

    Logger logger = LoggerFactory.getLogger(PetMapperImpl.class);
    private final DiagnosisMapper diagnosisMapper;
    private final GenderConverter genderConverter;

    @Autowired
    public PetMapperImpl(DiagnosisMapper diagnosisMapper, GenderConverter genderConverter) {
        this.diagnosisMapper = diagnosisMapper;
        this.genderConverter = genderConverter;
    }


    @Override
    public Pet petRequestDtoToPet(PetRequestDto petRequestDto) {

        Pet pet = new Pet();

        pet.setName(petRequestDto.getName());
        pet.setBornAt(petRequestDto.getBornAt());
        pet.setGender(genderConverter.convertToEntityAttribute(petRequestDto.getGender()));

        return pet;
    }

    @Override
    public Pet petRequestDtoToPet(Long petId, PetRequestDto petRequestDto) {
        Pet pet = new Pet();

        pet.setId(petId);
        pet.setName(petRequestDto.getName());
        pet.setBornAt(petRequestDto.getBornAt());
        pet.setGender(genderConverter.convertToEntityAttribute(petRequestDto.getGender()));

        return pet;
    }

    @Override
    public PetResponseDto petToPetResponseDto(Pet pet) {

        PetResponseDto petResponseDto = new PetResponseDto();

        petResponseDto.setPetId(pet.getId());
        petResponseDto.setName(pet.getName());
        petResponseDto.setGender(genderConverter.convertToDatabaseColumn(pet.getGender()));
        petResponseDto.setBornAt(pet.getBornAt());
        petResponseDto.setAge(pet.getAge());
        petResponseDto.setCreatedAt(pet.getCreatedAt());

        return petResponseDto;
    }

    @Override
    public List<PetResponseDto> petListToPetDtoList(Collection<Pet> petCollection) {

        List<PetResponseDto> petToPetResponseDtoList = new ArrayList<>();

        for (Pet pet : petCollection) {

            PetResponseDto petResponseDto = petToPetResponseDto(pet);

            petToPetResponseDtoList.add(petResponseDto);
        }

        return petToPetResponseDtoList;
    }

    //    @Override
//    public List<PetDto> petListToPetDtoList(Collection<Pet> petCollection) {
//
//        List<PetDto> petToPetDtoList = new ArrayList<>();
//
//        for (Pet pet : petCollection) {
//
//            PetDto petDto = petToPetDto(pet);
//
//            petToPetDtoList.add(petDto);
//        }
//
//        return petToPetDtoList;
//    }
}
