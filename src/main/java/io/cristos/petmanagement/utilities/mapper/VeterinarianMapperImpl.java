package io.cristos.petmanagement.utilities.mapper;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class VeterinarianMapperImpl implements VeterinarianMapper {

    @Override
    public VeterinarianDto veterinarianToVeterinarianDto(Veterinarian veterinarian) {

        VeterinarianDto veterinarianDto = new VeterinarianDto();

        veterinarianDto.setId(veterinarian.getId());
        veterinarianDto.setSpeciality(veterinarian.getSpeciality());
        veterinarianDto.setDateOfCreation(veterinarian.getDateOfCreation());

        return veterinarianDto;
    }

    @Override
    public Veterinarian veterinarianDtoToVeterinarian(VeterinarianDto veterinarianDto) {

        Veterinarian veterinarian = new Veterinarian();

        veterinarian.setId(veterinarianDto.getId());
        veterinarian.setSpeciality(veterinarianDto.getSpeciality());
        veterinarian.setDateOfCreation(veterinarianDto.getDateOfCreation());

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
