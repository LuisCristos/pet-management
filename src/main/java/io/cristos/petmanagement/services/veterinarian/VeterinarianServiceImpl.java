package io.cristos.petmanagement.services.veterinarian;

import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
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
    public Veterinarian saveVeterinarian(VeterinarianDto veterinarianDto) {

        return veterinarianRepository.save(veterinarianMapper.veterinarianDtoToVeterinarian(veterinarianDto));
    }

    @Override
    public List<VeterinarianDto> getAllVeterinarians() {

        Collection<Veterinarian> veterinarianCollection = veterinarianRepository.findAll();

        if (veterinarianCollection.isEmpty()) {
            logger.info("getAllVeterinarians(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        return veterinarianMapper.veterinarianListToVeterinarianDtoList(veterinarianCollection);
    }

    @Override
    public VeterinarianDto findVeterinarianById(Long id) {

        Optional<Veterinarian> optionalVeterinarian = Optional.ofNullable(veterinarianRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findVeterinarianById().", "Veterinarian with id: " + id + " cannot be found because it does not exist.",
                            new NotFoundException("Veterinarian with id: " + id + " cannot be found because it does not exist."));

                    throw new NotFoundException("Veterinarian with id: " + id + " cannot be found because it does not exist.");
                }));

        return veterinarianMapper.veterinarianToVeterinarianDto(optionalVeterinarian.get());
    }

    @Override
    public void deleteVeterinarianById(Long id) {

        boolean exists = veterinarianRepository.existsById(id);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "deleteVeterinarianById().", "Veterinarian with id: " + id + " cannot be deleted because it does not exist.",
                    new NotFoundException("Veterinarian with id: " + id + " cannot be found because it does not exist."));

            throw new NotFoundException("Veterinarian ID: " + id + " cannot be deleted because it does not exist.");
        }

        veterinarianRepository.deleteById(id);

    }

    @Override
    public Veterinarian updateVeterinarian(Long id, VeterinarianDto veterinarianDto) {

        boolean exists = veterinarianRepository.existsById(id);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "updateVeterinarian().", "Veterinarian with id: " + id + " cannot be updated because it does not exist.",
                    new NotFoundException("Veterinarian with id: " + id + " cannot be updated because it does not exist."));

            throw new NotFoundException("Veterinarian ID: " + id + " cannot be updated because it does not exist.");
        }

        return veterinarianRepository.save(
                veterinarianMapper.veterinarianDtoToVeterinarian(veterinarianDto));
    }
}
