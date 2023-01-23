package io.cristos.petmanagement.services.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import io.cristos.petmanagement.repositories.diagnosis.DiagnosisRepository;
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
    private final DiagnosisMapper diagnosisMapper;

    @Autowired
    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, DiagnosisMapper diagnosisMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.diagnosisMapper = diagnosisMapper;
    }

    @Override
    public Diagnosis saveDiagnosis(DiagnosisDto diagnosisDto) {

        return diagnosisRepository.save(diagnosisMapper.diagnosisDtoToDiagnosis(diagnosisDto));
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
    public DiagnosisDto findDiagnosisById(Long diagnosisId) {

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
    public void deleteDiagnosis(Long diagnosisID) {

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
    public Diagnosis updateDiagnosis(Long diagnosisId, DiagnosisDto diagnosisDto) {

        boolean exists = diagnosisRepository.existsById(diagnosisId);

        if (!exists) {
            logger.warn("{}, {}! An exception occurred!",
                    "updateDiagnosis().", "Diagnosis with id: " + diagnosisId + " cannot be updated because it does not exist.",
                    new NotFoundException("Diagnosis with id: " + diagnosisId + " cannot be deleted because it does not exist."));

            throw new NotFoundException("Diagnosis ID: " + diagnosisId + " cannot be updated because it does not exist.");
        }

        return diagnosisRepository.save(diagnosisMapper.diagnosisDtoToDiagnosis(diagnosisDto));
    }
}
