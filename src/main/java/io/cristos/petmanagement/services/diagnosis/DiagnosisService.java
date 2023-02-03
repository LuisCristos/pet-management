package io.cristos.petmanagement.services.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    Diagnosis saveDiagnosisToPet(Long petId, DiagnosisDto diagnosisDto);

    List<DiagnosisDto> getAllDiagnosisFromPet(Long petId);

    DiagnosisDto findDiagnosisByPetId(Long petId, Long diagnosisId);

    void deleteDiagnosisFromPet(Long petId, Long diagnosisID);

    Diagnosis updateDiagnosisFromPet(Long petId, Long diagnosisId, DiagnosisDto diagnosisDto);
}
