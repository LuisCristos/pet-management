package io.cristos.petmanagement.services.pet;

import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.repositories.pet.PetRepository;
import io.cristos.petmanagement.utilities.mapper.pet.PetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    @Override
    public Pet savePet(PetDto petDto) {

        return petRepository.save(petMapper.petDtoToPet(petDto));
    }

    @Override
    public List<PetDto> getAllPets() {

        Collection<Pet> petCollection = petRepository.findAll();

        if (petCollection.isEmpty()) {

            logger.info("getAllPets(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        return petMapper.petListToPetDtoList(petCollection);
    }

    @Override
    public PetDto findPetById(Long id) throws NotFoundException {

        Optional<Pet> optionalPet = Optional.ofNullable(petRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findCustomerById().", "Pet with id: " + id + " cannot be found because it does not exist.",
                            new NotFoundException("Pet ID: " + id + " cannot be found because it does not exist."));

                    throw new NotFoundException("Pet with id: " + id + " cannot be found.");
                }));

        return petMapper.petToPetDto(optionalPet.get());
    }

    @Override
    public void deletePetById(Long id) {

        boolean exists = petRepository.existsById(id);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "deletePetById().", "Pet with id: " + id + " cannot be deleted because it does not exist.",
                    new NotFoundException("Pet ID: " + id + " cannot be deleted because it does not exist."));

            throw new NotFoundException("Pet ID: " + id + " cannot be deleted because it does not exist.");
        }

        petRepository.deleteById(id);
    }

    @Override
    public Pet updatePet(Long id, PetDto petDto) {

        boolean exists = petRepository.existsById(id);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "updatePet().", "Pet with id: " + id + " cannot be updated because it does not exist.",
                    new NotFoundException("Pet ID: " + id + " cannot be updated because it does not exist."));

            throw new NotFoundException("Pet ID: " + id + " cannot be updated because it does not exist.");
        }

        return petRepository.save(petMapper.petDtoToPet(petDto));
    }
}
