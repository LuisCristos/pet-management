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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiagnosisServiceImpl implements DiagnosisService {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisServiceImpl.class);
    private final DiagnosisRepository diagnosisRepository;
    private final PetRepository petRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Autowired
    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, PetRepository petRepository,
                                DiagnosisMapper diagnosisMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.petRepository = petRepository;
        this.diagnosisMapper = diagnosisMapper;
    }

    @Override
    public Diagnosis saveDiagnosisToPet(Long petId, DiagnosisDto diagnosisDto) {

        final String methodName = "saveDiagnosisToPet()";
        Pet pet = getPetToSaveOrUpdateDiagnosis(petId, methodName);

        Diagnosis diagnosis = diagnosisMapper.diagnosisDtoToDiagnosis(diagnosisDto);

        diagnosis.setPet(pet);
        pet.addDiagnosis(diagnosis);

        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public List<DiagnosisDto> getAllDiagnosisFromPet(Long petId) {

        final String methodName = "getAllDiagnosisFromPet()";
        checkIfPetExists(petId, methodName);

        List<Diagnosis> diagnosisList = diagnosisRepository.findDiagnosisByPetId(petId);

        if (diagnosisList.isEmpty()) {

            return Collections.emptyList();
        }

        return diagnosisMapper.diagnosisListToDiagnosisDtoList(diagnosisList);
    }

    @Override
    public DiagnosisDto findDiagnosisByPetId(Long petId, Long diagnosisId) {

        final String methodName = "findDiagnosisByPetId()";
        checkIfPetExists(petId, methodName);

        Optional<Diagnosis> optionalDiagnosis = Optional.of(diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findDiagnosisById().", "Diagnosis with id: " + diagnosisId + " cannot be found because it does not exist.",
                            new NotFoundException("Diagnosis with id: " + diagnosisId + " cannot be found because it does not exist."));

                    return new NotFoundException("Diagnosis with id: " + diagnosisId + " cannot be found because it does not exist.");
                }));

        return diagnosisMapper.diagnosisToDiagnosisDto(optionalDiagnosis.get());
    }

    @Override
    public void deleteDiagnosisFromPet(Long petId, Long diagnosisId) {

        final String methodName = "deleteDiagnosisFromPet()";
        final String deleteOrUpdate = "deleted";
        checkIfPetExists(petId, methodName);

        checkIfDiagnosisExists(diagnosisId, methodName, deleteOrUpdate);

        diagnosisRepository.deleteById(diagnosisId);
    }

    @Override
    public Diagnosis updateDiagnosisFromPet(Long petId, Long diagnosisId, DiagnosisDto diagnosisDto) {

        final String methodName = "saveDiagnosisToPet()";
        final String deleteOrUpdate = "updated";
        Pet pet = getPetToSaveOrUpdateDiagnosis(petId, methodName);

        checkIfDiagnosisExists(diagnosisId, methodName, deleteOrUpdate);

        Diagnosis diagnosis = diagnosisMapper.diagnosisDtoToDiagnosis(diagnosisDto);

        diagnosis.setPet(pet);
        pet.addDiagnosis(diagnosis);

        return diagnosisRepository.save(diagnosis);
    }

    private void checkIfDiagnosisExists(Long diagnosisId, String methodName, String deleteOrUpdate) {

        boolean exists = diagnosisRepository.existsById(diagnosisId);

        if (!exists) {
            logger.warn("{}, {}! An exception occurred!",
                    "" + methodName + ".", "Diagnosis with id: " + diagnosisId + " cannot be " + deleteOrUpdate + " because it does not exist.",
                    new NotFoundException("Diagnosis with id: " + diagnosisId + " cannot be " + deleteOrUpdate + " because it does not exist."));

            throw new NotFoundException("Diagnosis with id: " + diagnosisId + " cannot be " + deleteOrUpdate + " because it does not exist.");
        }
    }

    private void checkIfPetExists(Long petId, String methodName) {

        boolean petExists = petRepository.existsById(petId);

        if (!petExists) {

            logger.warn("{}, {}! An exception occurred!",
                    "" + methodName + ".", "Pet with id: " + petId + " cannot be found because it does not exist.",
                    new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist."));

            throw new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist.");
        }
    }

    private Pet getPetToSaveOrUpdateDiagnosis(Long petId, String methodName) {

        Optional<Pet> optionalPet = Optional.of(petRepository.findById(petId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "" + methodName + ".", "Pet with id: " + petId + " cannot be found because it does not exist.",
                            new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist."));

                    throw new NotFoundException("Pet ID: " + petId + " cannot be found because it does not exist.");
                }));

        return optionalPet.get();
    }
}
