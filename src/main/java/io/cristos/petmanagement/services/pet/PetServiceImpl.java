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

                    throw new NotFoundException("Pet ID: " + id + " cannot be found because it does not exist.");
                }));

        return petMapper.petToPetDto(optionalPet.get());
    }

    @Override
    public void deletePetById(Long petId) {

        final String deleted = "deleted";
        final String methodName = "deletePetById()";
        checkIfPetExistsById(petId, methodName, deleted);

        petRepository.deleteById(petId);
    }

    @Override
    public Pet updatePetById(Long petId, PetDto petDto) {

        final String updated = "updated";
        final String methodName = "updatePetById()";
        checkIfPetExistsById(petId, methodName, updated);

        return petRepository.save(petMapper.petDtoToPet(petDto));
    }

    private void checkIfPetExistsById(Long petId, String methodName, String value) {

        boolean exists = petRepository.existsById(petId);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "" + methodName + ".", "Pet with id: " + petId + " cannot be " + value + " because it does not exist.",
                    new NotFoundException("Pet ID: " + petId + " cannot be " + value + " because it does not exist."));

            throw new NotFoundException("Pet ID: " + petId + " cannot be " + value + " because it does not exist.");
        }

    }
}
