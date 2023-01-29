package io.cristos.petmanagement.services.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    Diagnosis saveDiagnosisToPet(Long petId, DiagnosisDto diagnosisDto);

    List<DiagnosisDto> getAllDiagnosis();

    DiagnosisDto findDiagnosisById(Long petId, Long diagnosisId);

    void deleteDiagnosis(Long petId, Long diagnosisID);

    Diagnosis updateDiagnosis(Long petId, Long diagnosisId, DiagnosisDto diagnosisDto);
}
