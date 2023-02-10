package io.cristos.petmanagement.utilities.mapper.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import io.cristos.petmanagement.utilities.mapper.genderconverter.GenderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class VeterinarianMapperImpl implements VeterinarianMapper {

    private final GenderConverter genderConverter;

    @Autowired
    public VeterinarianMapperImpl(GenderConverter genderConverter) {
        this.genderConverter = genderConverter;
    }

    @Override
    public VeterinarianDto veterinarianToVeterinarianDto(Veterinarian veterinarian) {

        VeterinarianDto veterinarianDto = new VeterinarianDto();

        veterinarianDto.setVeterinarianId(veterinarian.getId());
        veterinarianDto.setFirstName(veterinarian.getFirstName());
        veterinarianDto.setLastName(veterinarian.getLastName());
        veterinarianDto.setDateOfBirth(veterinarian.getDateOfBirth());
        veterinarianDto.setSpeciality(veterinarian.getSpeciality());
        veterinarianDto.setDateOfCreation(veterinarian.getDateOfCreation());
        veterinarianDto.setAge(veterinarian.getAge());
        veterinarianDto.setGender(genderConverter.convertToDatabaseColumn(veterinarian.getGender()));

        return veterinarianDto;
    }

    @Override
    public Veterinarian veterinarianDtoToVeterinarian(VeterinarianDto veterinarianDto) {

        Veterinarian veterinarian = new Veterinarian();

        veterinarian.setId(veterinarianDto.getVeterinarianId());
        veterinarian.setFirstName(veterinarianDto.getFirstName());
        veterinarian.setLastName(veterinarianDto.getLastName());
        veterinarian.setDateOfBirth(veterinarianDto.getDateOfBirth());
        veterinarian.setSpeciality(veterinarianDto.getSpeciality());
        veterinarian.setDateOfCreation(veterinarianDto.getDateOfCreation());
        veterinarian.setAge(veterinarianDto.getAge());
        veterinarian.setGender(genderConverter.convertToEntityAttribute(veterinarianDto.getGender()));

        return veterinarian;
    }

    @Override
    public List<VeterinarianDto> veterinarianListToVeterinarianDtoList(Collection<Veterinarian> veterinarianCollection) {

        List<VeterinarianDto> veterinarianDtosList = new ArrayList<>();

        for (Veterinarian veterinarian : veterinarianCollection) {

            VeterinarianDto veterinarianDto = veterinarianToVeterinarianDto(veterinarian);

            veterinarianDtosList.add(veterinarianDto);
        }

        return veterinarianDtosList;
    }
}
