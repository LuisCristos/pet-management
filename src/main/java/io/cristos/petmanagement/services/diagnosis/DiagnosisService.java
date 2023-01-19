package io.cristos.petmanagement.services.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    Diagnosis saveDiagnosis(DiagnosisDto diagnosisDto);

    List<DiagnosisDto> getAllDiagnosis();

    DiagnosisDto findDiagnosisById(Long diagnosisId);

    void deleteDiagnosis(Long diagnosisID);

    Diagnosis updateDiagnosis(Long diagnosisId, DiagnosisDto diagnosisDto);
}
