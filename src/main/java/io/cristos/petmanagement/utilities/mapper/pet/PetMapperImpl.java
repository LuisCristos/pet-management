package io.cristos.petmanagement.utilities.mapper.pet;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
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
    public PetDto petToPetDto(Pet pet) {

        PetDto petDto = new PetDto();

        petDto.setId(pet.getId());
        petDto.setName(pet.getName());
        petDto.setGender(genderConverter.convertToDatabaseColumn(pet.getGender()));
        petDto.setDateOfBirth(pet.getBornAt());
        petDto.setDateOfCreation(pet.getCreatedAt());

        for (Diagnosis diagnosis : pet.getDiagnosisList()) {

            DiagnosisDto diagnosisDto = diagnosisMapper.diagnosisToDiagnosisDto(diagnosis);

            petDto.addDiagnosis(diagnosisDto);
        }

        return petDto;
    }

    @Override
    public Pet petDtoToPet(PetDto petDto) {

        Pet pet = new Pet();

        pet.setId(petDto.getId());
        pet.setName(petDto.getName());
        pet.setGender(genderConverter.convertToEntityAttribute(petDto.getGender()));
        pet.setBornAt(petDto.getDateOfBirth());
        pet.setCreatedAt(petDto.getDateOfCreation());

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
