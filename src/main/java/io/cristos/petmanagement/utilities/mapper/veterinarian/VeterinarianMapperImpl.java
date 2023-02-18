package io.cristos.petmanagement.utilities.mapper.veterinarian;


import io.cristos.petmanagement.dtos.request.veterinarian.VeterinarianRequestDto;
import io.cristos.petmanagement.dtos.response.veterinarian.VeterinarianResponseDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import io.cristos.petmanagement.utilities.mapper.contact.ContactMapper;
import io.cristos.petmanagement.utilities.mapper.genderconverter.GenderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class VeterinarianMapperImpl implements VeterinarianMapper {

    private final GenderConverter genderConverter;
    private final ContactMapper contactMapper;

    @Autowired
    public VeterinarianMapperImpl(GenderConverter genderConverter, ContactMapper contactMapper) {
        this.genderConverter = genderConverter;
        this.contactMapper = contactMapper;
    }


    @Override
    public Veterinarian veterinarianRequestDtoToVeterinarian(VeterinarianRequestDto veterinarianRequestDto) {

        Veterinarian veterinarian = new Veterinarian();

        veterinarian.setFirstName(veterinarianRequestDto.getFirstName());
        veterinarian.setLastName(veterinarianRequestDto.getLastName());
        veterinarian.setGender(genderConverter.convertToEntityAttribute(veterinarianRequestDto.getGender()));
        veterinarian.setBornAt(veterinarianRequestDto.getBornAt());
        veterinarian.setSpeciality(veterinarianRequestDto.getSpeciality());

        return veterinarian;
    }

    @Override
    public Veterinarian veterinarianRequestDtoToVeterinarian(Long veterinarianId, VeterinarianRequestDto veterinarianRequestDto) {

        Veterinarian veterinarian = new Veterinarian();

        veterinarian.setId(veterinarianId);
        veterinarian.setFirstName(veterinarianRequestDto.getFirstName());
        veterinarian.setLastName(veterinarianRequestDto.getLastName());
        veterinarian.setGender(genderConverter.convertToEntityAttribute(veterinarianRequestDto.getGender()));
        veterinarian.setBornAt(veterinarianRequestDto.getBornAt());
        veterinarian.setSpeciality(veterinarianRequestDto.getSpeciality());

        return veterinarian;
    }

    @Override
    public VeterinarianResponseDto veterinarianToVeterinarianResponseDto(Veterinarian veterinarian) {

        VeterinarianResponseDto veterinarianResponseDto = new VeterinarianResponseDto();

        veterinarianResponseDto.setVeterinarianId(veterinarian.getId());
        veterinarianResponseDto.setFirstName(veterinarian.getFirstName());
        veterinarianResponseDto.setLastName(veterinarian.getLastName());
        veterinarianResponseDto.setBornAt(veterinarian.getBornAt());
        veterinarianResponseDto.setGender(genderConverter.convertToDatabaseColumn(veterinarian.getGender()));
        veterinarianResponseDto.setSpeciality(veterinarian.getSpeciality());
        veterinarianResponseDto.setCreatedAt(veterinarian.getCreatedAt());
        veterinarianResponseDto.setAge(veterinarian.getAge());

        return veterinarianResponseDto;
    }

    @Override
    public List<VeterinarianResponseDto> veterinarianListToVeterinarianResponseDtoList(Collection<Veterinarian> veterinarianCollection) {

        List<VeterinarianResponseDto> veterinarianResponseDtoList = new ArrayList<>();

        for (Veterinarian veterinarian : veterinarianCollection) {

            VeterinarianResponseDto veterinarianResponseDto = veterinarianToVeterinarianResponseDto(veterinarian);

            veterinarianResponseDtoList.add(veterinarianResponseDto);
        }

        return veterinarianResponseDtoList;
    }
}
