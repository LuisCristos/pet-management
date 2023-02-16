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

        veterinarian.setFirstName(veterinarianRequestDto.firstName());
        veterinarian.setLastName(veterinarianRequestDto.lastName());
        veterinarian.setGender(genderConverter.convertToEntityAttribute(veterinarianRequestDto.gender()));
        veterinarian.setBornAt(veterinarianRequestDto.bornAt());
        veterinarian.setSpeciality(veterinarianRequestDto.speciality());

        return veterinarian;
    }

    @Override
    public Veterinarian veterinarianRequestDtoToVeterinarian(Long veterinarianId, VeterinarianRequestDto veterinarianRequestDto) {

        Veterinarian veterinarian = new Veterinarian();

        veterinarian.setId(veterinarianId);
        veterinarian.setFirstName(veterinarianRequestDto.firstName());
        veterinarian.setLastName(veterinarianRequestDto.lastName());
        veterinarian.setGender(genderConverter.convertToEntityAttribute(veterinarianRequestDto.gender()));
        veterinarian.setBornAt(veterinarianRequestDto.bornAt());
        veterinarian.setSpeciality(veterinarianRequestDto.speciality());

        return veterinarian;
    }

    @Override
    public VeterinarianResponseDto veterinarianToVeterinarianResponseDto(Veterinarian veterinarian) {

        return new VeterinarianResponseDto(
                veterinarian.getId(),
                veterinarian.getFirstName(),
                veterinarian.getLastName(),
                veterinarian.getBornAt(),
                genderConverter.convertToDatabaseColumn(veterinarian.getGender()),
                veterinarian.getSpeciality(),
                veterinarian.getCreatedAt(),
                veterinarian.getAge()
        );
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

//    @Override
//    public VeterinarianDto veterinarianToVeterinarianDto(Veterinarian veterinarian) {
//
//        VeterinarianDto veterinarianDto = new VeterinarianDto();
//
//        veterinarianDto.setVeterinarianId(veterinarian.getId());
//        veterinarianDto.setFirstName(veterinarian.getFirstName());
//        veterinarianDto.setLastName(veterinarian.getLastName());
//        veterinarianDto.setDateOfBirth(veterinarian.getBornAt());
//        veterinarianDto.setSpeciality(veterinarian.getSpeciality());
//        veterinarianDto.setDateOfCreation(veterinarian.getCreatedAt());
//        veterinarianDto.setAge(veterinarian.getAge());
//        veterinarianDto.setGender(genderConverter.convertToDatabaseColumn(veterinarian.getGender()));
//
//        if (veterinarian.getContact() != null) {
//            veterinarianDto.setContactDto(contactMapper.contactToContactDto(veterinarian.getContact()));
//        }
//
//        return veterinarianDto;
//    }
//
//    @Override
//    public Veterinarian veterinarianDtoToVeterinarian(VeterinarianDto veterinarianDto) {
//
//        Veterinarian veterinarian = new Veterinarian();
//
//        veterinarian.setId(veterinarianDto.getVeterinarianId());
//        veterinarian.setFirstName(veterinarianDto.getFirstName());
//        veterinarian.setLastName(veterinarianDto.getLastName());
//        veterinarian.setBornAt(veterinarianDto.getDateOfBirth());
//        veterinarian.setSpeciality(veterinarianDto.getSpeciality());
//        veterinarian.setCreatedAt(veterinarianDto.getDateOfCreation());
//        veterinarian.setAge(veterinarianDto.getAge());
//        veterinarian.setGender(genderConverter.convertToEntityAttribute(veterinarianDto.getGender()));
//
//        if (veterinarianDto.getContactDto() != null) {
//            veterinarian.setContact(contactMapper.contactDtoToContact(veterinarianDto.getContactDto()));
//        }
//
//        return veterinarian;
//    }
}
