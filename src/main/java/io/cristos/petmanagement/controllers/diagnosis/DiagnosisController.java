package io.cristos.petmanagement.controllers.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DiagnosisController {

    ResponseEntity<DiagnosisDto> saveDiagnosisToPet(@PathVariable(name = "petId")
                                                    @NotNull
                                                    @Min(value = 1)
                                                    Long petId,
                                                    @Valid
                                                    @RequestBody DiagnosisDto diagnosisDto);

    ResponseEntity<List<DiagnosisDto>> getAllDiagnosesFromPet(@PathVariable(name = "petId")
                                                              @NotNull
                                                              @Min(value = 1)
                                                              Long petId);

    ResponseEntity<DiagnosisDto> findDiagnosisByPetId(@PathVariable(name = "petId")
                                                      @NotNull
                                                      @Min(value = 1)
                                                      Long petId,
                                                      @PathVariable(name = "diagnosisId")
                                                      @NotNull
                                                      @Min(value = 1)
                                                      Long diagnosisId);

    ResponseEntity<DiagnosisDto> deleteDiagnosisFromPet(@PathVariable("petId")
                                                        @NotNull
                                                        @Min(value = 1)
                                                        Long petId,
                                                        @PathVariable("diagnosisId")
                                                        @Min(value = 1)
                                                        Long diagnosisId);

    ResponseEntity<DiagnosisDto> updateDiagnosisFromPet(@PathVariable(name = "petId")
                                                        @NotNull
                                                        @Min(value = 1)
                                                        Long petId,
                                                        @PathVariable(name = "diagnosisId")
                                                        @NotNull
                                                        @Min(value = 1)
                                                        Long diagnosisId,
                                                        @Valid
                                                        @RequestBody DiagnosisDto diagnosisDto);
}

