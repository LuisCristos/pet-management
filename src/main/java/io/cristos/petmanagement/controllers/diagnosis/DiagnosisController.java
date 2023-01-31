package io.cristos.petmanagement.controllers.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.dtos.pet.PetDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DiagnosisController {

    ResponseEntity<DiagnosisDto> saveDiagnosisToPet(@PathVariable(name = "petId")
                                                    @Min(value = 1)
                                                    Long petId,
                                                    @Valid
                                                    @RequestBody DiagnosisDto diagnosisDto);

    ResponseEntity<PetDto> getAllDiagnosis(@PathVariable(name = "petId")
                                           @Min(value = 1)
                                           Long petId);

    ResponseEntity<DiagnosisDto> findDiagnosisById(@PathVariable(name = "petId")
                                                   @Min(value = 1)
                                                   Long petId,
                                                   @PathVariable(name = "diagnosisId")
                                                   @Min(value = 1)
                                                   Long diagnosisId);

    ResponseEntity<DiagnosisDto> deleteDiagnosisById(@PathVariable("petId")
                                                     @Min(value = 1)
                                                     Long petId,
                                                     @PathVariable("diagnosisId")
                                                     @Min(value = 1)
                                                     Long diagnosisId);

    ResponseEntity<DiagnosisDto> updateDiagnosisById(@PathVariable(name = "petId")
                                                     @Min(value = 1)
                                                     Long petId,
                                                     @PathVariable(name = "diagnosisId")
                                                     @Min(value = 1)
                                                     Long diagnosisId,
                                                     @Valid
                                                     @RequestBody DiagnosisDto diagnosisDto);
}

