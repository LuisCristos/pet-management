package io.cristos.petmanagement.controllers.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiagnosisController {

    ResponseEntity<DiagnosisDto> saveDiagnosisToPet(Long petId, DiagnosisDto diagnosisDto);

    ResponseEntity<List<DiagnosisDto>> getAllDiagnosis();

    ResponseEntity<DiagnosisDto> findDiagnosisById(Long petId, Long diagnosisId);

    ResponseEntity<DiagnosisDto> deleteDiagnosisById(Long petId, Long diagnosisId);

    ResponseEntity<DiagnosisDto> updateDiagnosisById(Long petId, Long diagnosisId, DiagnosisDto diagnosisDto);
}
