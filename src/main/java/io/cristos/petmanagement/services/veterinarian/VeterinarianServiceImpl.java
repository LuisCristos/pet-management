package io.cristos.petmanagement.services.veterinarian;

import io.cristos.petmanagement.dtos.request.veterinarian.VeterinarianRequestDto;
import io.cristos.petmanagement.dtos.response.veterinarian.VeterinarianResponseDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import io.cristos.petmanagement.repositories.veterinarian.VeterinarianRepository;
import io.cristos.petmanagement.utilities.mapper.veterinarian.VeterinarianMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class VeterinarianServiceImpl implements VeterinarianService {

    private final Logger logger = LoggerFactory.getLogger(VeterinarianServiceImpl.class);
    private final VeterinarianRepository veterinarianRepository;
    private final VeterinarianMapper veterinarianMapper;

    @Autowired
    public VeterinarianServiceImpl(VeterinarianRepository veterinarianRepository, VeterinarianMapper veterinarianMapper) {
        this.veterinarianRepository = veterinarianRepository;
        this.veterinarianMapper = veterinarianMapper;
    }

    @Override
    public Veterinarian saveVeterinarian(VeterinarianRequestDto veterinarianRequestDto) {

        return veterinarianRepository.save(
                veterinarianMapper.veterinarianRequestDtoToVeterinarian(veterinarianRequestDto));
    }


    @Override
    public Veterinarian saveVeterinarianContact(Veterinarian veterinarian) {
        return veterinarianRepository.save(veterinarian);
    }

    @Override
    public List<VeterinarianResponseDto> getAllVeterinarians() {

        Collection<Veterinarian> veterinarianCollection = veterinarianRepository.findAll();

        if (veterinarianCollection.isEmpty()) {
            logger.info("getAllVeterinarians(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        return veterinarianMapper.veterinarianListToVeterinarianResponseDtoList(veterinarianCollection);
    }

    @Override
    public VeterinarianResponseDto findVeterinarianById(Long veterinarianId) {

        final String action = "found";
        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId, action);

        return veterinarianMapper.veterinarianToVeterinarianResponseDto(veterinarian);
    }

    @Override
    public void deleteVeterinarianById(Long veterinarianId) {

        final String action = "deleted";
        returnVeterinarianIfExists(veterinarianId, action);

        veterinarianRepository.deleteById(veterinarianId);
    }

    @Override
    public Veterinarian updateVeterinarian(Long veterinarianId, VeterinarianRequestDto veterinarianRequestDto) {

        final String action = "updated";
        returnVeterinarianIfExists(veterinarianId, action);

        return veterinarianRepository.save(
                veterinarianMapper.veterinarianRequestDtoToVeterinarian(veterinarianId, veterinarianRequestDto));
    }

    @Override
    public Veterinarian returnVeterinarianIfExists(Long veterinarianId, String action) {

        Optional<Veterinarian> optionalVeterinarian = Optional.ofNullable(veterinarianRepository.findById(veterinarianId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist.",
                            new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist."));

                    throw new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist.");
                }));

        return optionalVeterinarian.get();
    }

    @Override
    public Veterinarian returnVeterinarianIfExists(Long veterinarianId) {

        Optional<Veterinarian> optionalVeterinarian = Optional.ofNullable(veterinarianRepository.findById(veterinarianId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Veterinarian with id: " + veterinarianId + " cannot be found.",
                            new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be found."));

                    throw new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be found.");
                }));

        return optionalVeterinarian.get();
    }
}
