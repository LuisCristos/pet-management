package io.cristos.petmanagement.controllers.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.dtos.pet.PetDto;
import org.springframework.http.ResponseEntity;

public interface DiagnosisController {

    ResponseEntity<DiagnosisDto> saveDiagnosisToPet(Long petId, DiagnosisDto diagnosisDto);

    ResponseEntity<PetDto> getAllDiagnosis(Long petId);

    ResponseEntity<DiagnosisDto> findDiagnosisById(Long petId, Long diagnosisId);

    ResponseEntity<DiagnosisDto> deleteDiagnosisById(Long petId, Long diagnosisId);

    ResponseEntity<DiagnosisDto> updateDiagnosisById(Long petId, Long diagnosisId, DiagnosisDto diagnosisDto);
}
