package io.cristos.petmanagement.services.diagnosis;

import io.cristos.petmanagement.dtos.request.diagnosis.DiagnosisRequestDto;
import io.cristos.petmanagement.dtos.response.diagnosis.DiagnosisResponseDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    Diagnosis saveDiagnosis(DiagnosisRequestDto diagnosisRequestDto);

    DiagnosisResponseDto findDiagnosisById(Long diagnosisID);

    List<DiagnosisResponseDto> getAllDiagnosis();

    Diagnosis updateDiagnosis(Long diagnosisId, DiagnosisRequestDto diagnosisRequestDto);

    void deleteDiagnosisById(Long diagnosisId);

    Diagnosis returnDiagnosisIfExists(Long diagnosisId);


//    Diagnosis saveDiagnosisToPet(Long petId, DiagnosisDto diagnosisDto);
//
//    List<DiagnosisDto> getAllDiagnosisFromPet(Long petId);
//
//    DiagnosisDto findDiagnosisByPetId(Long petId, Long diagnosisId);
//
//    void deleteDiagnosisFromPet(Long petId, Long diagnosisID);
//
//    Diagnosis updateDiagnosisFromPet(Long petId, Long diagnosisId, DiagnosisDto diagnosisDto);
}
