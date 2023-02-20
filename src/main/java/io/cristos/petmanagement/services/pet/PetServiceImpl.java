package io.cristos.petmanagement.services.pet;

import io.cristos.petmanagement.dtos.request.pet.PetRequestDto;
import io.cristos.petmanagement.dtos.response.pet.PetResponseDto;
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
    public Pet savePet(PetRequestDto petRequestDto) {

        return petRepository.save(petMapper.petRequestDtoToPet(petRequestDto));
    }

    @Override
    public List<PetResponseDto> getAllPets() {

        Collection<Pet> petCollection = petRepository.findAll();

        if (petCollection.isEmpty()) {

            logger.info("getAllPets(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        return petMapper.petListToPetDtoList(petCollection);
    }

    @Override
    public PetResponseDto findPetById(Long petId) {

        Pet pet = returnPetIfExists(petId);

        return petMapper.petToPetResponseDto(pet);
    }

    @Override
    public void deletePetById(Long petId) {

        returnPetIfExists(petId);

        petRepository.deleteById(petId);
    }

    @Override
    public Pet updatePetById(Long petId, PetRequestDto petRequestDto) {

        returnPetIfExists(petId);

        return petRepository.save(petMapper.petRequestDtoToPet(petId, petRequestDto));
    }

    @Override
    public Pet returnPetIfExists(Long petId) {

        Optional<Pet> optionalPet = Optional.ofNullable(petRepository.findById(petId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Pet with id: " + petId + " cannot be found.",
                            new NotFoundException("Pet with id: " + petId + " cannot be found."));

                    throw new NotFoundException("Pet with id: " + petId + " cannot be found.");
                }));

        return optionalPet.get();
    }
}
