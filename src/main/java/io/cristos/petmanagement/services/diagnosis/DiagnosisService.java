package io.cristos.petmanagement.services.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;

public interface DiagnosisService {

    Diagnosis saveDiagnosisToPet(Long petId, DiagnosisDto diagnosisDto);

    PetDto getAllDiagnosis(Long petId);

    DiagnosisDto findDiagnosisById(Long petId, Long diagnosisId);

    void deleteDiagnosis(Long petId, Long diagnosisID);

    Diagnosis updateDiagnosis(Long petId, Long diagnosisId, DiagnosisDto diagnosisDto);
}
