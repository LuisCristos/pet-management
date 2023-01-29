package io.cristos.petmanagement.services.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.repositories.diagnosis.DiagnosisRepository;
import io.cristos.petmanagement.repositories.pet.PetRepository;
import io.cristos.petmanagement.utilities.mapper.diagnosis.DiagnosisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisServiceImpl.class);
    private final DiagnosisRepository diagnosisRepository;
    private final PetRepository petRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Autowired
    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, PetRepository petRepository, DiagnosisMapper diagnosisMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.petRepository = petRepository;
        this.diagnosisMapper = diagnosisMapper;
    }

    @Override
    public Diagnosis saveDiagnosisToPet(Long petId, DiagnosisDto diagnosisDto) {

        Optional<Pet> optionalPet = Optional.ofNullable(petRepository.findById(petId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findCustomerById().", "Pet with id: " + petId + " cannot be found because it does not exist.",
                            new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist."));

                    throw new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist.");
                }));

        Diagnosis diagnosis = diagnosisMapper.diagnosisDtoToDiagnosis(diagnosisDto);

        Pet pet = optionalPet.get();

        diagnosis.setPet(pet);
        pet.addDiagnosis(diagnosis);

        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public List<DiagnosisDto> getAllDiagnosis() {

        Collection<Diagnosis> diagnosisCollection = diagnosisRepository.findAll();

        if (diagnosisCollection.isEmpty()) {

            logger.info("getAllDiagnosis(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        return diagnosisMapper.diagnosisListToDiagnosisDtoList(diagnosisCollection);
    }

    @Override
    public DiagnosisDto findDiagnosisById(Long petId, Long diagnosisId) {

        Optional<Pet> optionalPet = Optional.ofNullable(petRepository.findById(petId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findCustomerById().", "Pet with id: " + petId + " cannot be found because it does not exist.",
                            new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist."));

                    throw new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist.");
                }));

        boolean exists = diagnosisRepository.existsById(diagnosisId);

        Optional<Diagnosis> optionalDiagnosis = Optional.ofNullable(diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findDiagnosisById().", "Diagnosis with id: " + diagnosisId + " cannot be found because it does not exist.",
                            new NotFoundException("Diagnosis with id: " + diagnosisId + " cannot be found because it does not exist."));

                    return new NotFoundException("Diagnosis with id: " + diagnosisId + " cannot be found because it does not exist.");
                }));

        return diagnosisMapper.diagnosisToDiagnosisDto(optionalDiagnosis.get());
    }

    @Override
    public void deleteDiagnosis(Long petId, Long diagnosisID) {

        Optional<Pet> optionalPet = Optional.ofNullable(petRepository.findById(petId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findCustomerById().", "Pet with id: " + petId + " cannot be found because it does not exist.",
                            new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist."));

                    throw new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist.");
                }));

        boolean exists = diagnosisRepository.existsById(diagnosisID);

        if (!exists) {
            logger.warn("{}, {}! An exception occurred!",
                    "deleteDiagnosis().", "Diagnosis with id: " + diagnosisID + " cannot be deleted because it does not exist.",
                    new NotFoundException("Diagnosis with id: " + diagnosisID + " cannot be deleted because it does not exist."));

            throw new NotFoundException("Diagnosis ID: " + diagnosisID + " cannot be deleted because it does not exist.");
        }

        diagnosisRepository.deleteById(diagnosisID);
    }

    @Override
    public Diagnosis updateDiagnosis(Long petId, Long diagnosisId, DiagnosisDto diagnosisDto) {

        Optional<Pet> optionalPet = Optional.ofNullable(petRepository.findById(petId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findCustomerById().", "Pet with id: " + petId + " cannot be found because it does not exist.",
                            new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist."));

                    throw new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist.");
                }));

        boolean exists = diagnosisRepository.existsById(diagnosisId);

        if (!exists) {
            logger.warn("{}, {}! An exception occurred!",
                    "updateDiagnosis().", "Diagnosis with id: " + diagnosisId + " cannot be updated because it does not exist.",
                    new NotFoundException("Diagnosis with id: " + diagnosisId + " cannot be deleted because it does not exist."));

            throw new NotFoundException("Diagnosis ID: " + diagnosisId + " cannot be updated because it does not exist.");
        }

        Diagnosis diagnosis = diagnosisMapper.diagnosisDtoToDiagnosis(diagnosisDto);

        Pet pet = optionalPet.get();

        diagnosis.setPet(pet);
        pet.addDiagnosis(diagnosis);

        return diagnosisRepository.save(diagnosis);
    }
}
