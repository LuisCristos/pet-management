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

        logger.info(veterinarianDto + " saved to database.");

        return veterinarianRepository.save(veterinarianMapper.veterinarianDtoToVeterinarian(veterinarianDto));
    }

    @Override
    public List<VeterinarianDto> getAllVeterinarians() {

        Collection<Veterinarian> veterinarianCollection = veterinarianRepository.findAll();

        if (veterinarianCollection.isEmpty()) {
            return Collections.emptyList();
        }

        logger.info("getAllVeterinarians(). Retrieved all veterinarians.");

        return veterinarianMapper.veterinarianListToVeterinarianDtoList(veterinarianCollection);
    }

    @Override
    public VeterinarianDto findVeterinarianById(Long id) throws NotFoundException {

        Optional<Veterinarian> optionalVeterinarian = veterinarianRepository.findById(id);

        if (optionalVeterinarian.isEmpty()) {

            logger.warn("{}, {}! An exception occurred!",
                    "findVeterinarianById().", "Veterinarian with id: " + id + " cannot be found because it does not exist.",
                    new NotFoundException("Veterinarian with id: " + id + " not found"));

            throw new NotFoundException("Veterinarian with id: " + id + " cannot be found.");
        }

        logger.info("Retrieved Veterinarian with id: " + id);

        return veterinarianMapper.veterinarianToVeterinarianDto(optionalVeterinarian.get());
    }

    @Override
    public void deleteVeterinarianById(Long id) throws NotFoundException {

        boolean exists = veterinarianRepository.existsById(id);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "deleteVeterinarianById().", "Veterinarian with id: " + id + " cannot be deleted because it does not exist.",
                    new NotFoundException("Veterinarian with id: " + id + " not found"));

            throw new NotFoundException("Veterinarian ID: " + id + " cannot be deleted because it does not exist.");
        }

        logger.info("Deleted Veterinarian with id: " + id);

        veterinarianRepository.deleteById(id);

    }

    @Override
    public Veterinarian updateVeterinarian(Long id, VeterinarianDto veterinarianDto) throws NotFoundException {

        boolean exists = veterinarianRepository.existsById(id);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "updateVeterinarian().", "Veterinarian with id: " + id + " cannot be updated because it does not exist.",
                    new NotFoundException("Veterinarian with id: " + id + " not found"));

            throw new NotFoundException("Veterinarian ID: " + id + " cannot be updated because it does not exist.");

        }

        logger.info("Veterinarian customer with id: " + id);

        return veterinarianRepository.save(
                veterinarianMapper.veterinarianDtoToVeterinarian(veterinarianDto));
    }
}
