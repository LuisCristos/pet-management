package io.cristos.petmanagement.controllers.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import io.cristos.petmanagement.services.diagnosis.DiagnosisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Validated
public class DiagnosisControllerImpl implements DiagnosisController {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisControllerImpl.class);
    private DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisControllerImpl(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Override
    @PostMapping(value = "/pets/{petId}/diagnosis")
    public ResponseEntity<DiagnosisDto> saveDiagnosisToPet(@PathVariable(name = "petId")
                                                           @NotNull
                                                           @Min(value = 1)
                                                           Long petId,
                                                           @Valid
                                                           @RequestBody DiagnosisDto diagnosisDto) {

        Diagnosis diagnosis = diagnosisService.saveDiagnosisToPet(petId, diagnosisDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{diagnosisId}")
                .buildAndExpand(diagnosis.getId())
                .toUri();

        logger.info(diagnosisDto + " saved to Database.");

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping("/pets/{petId}/diagnosis")
    public ResponseEntity<List<DiagnosisDto>> getAllDiagnosesFromPet(@PathVariable(name = "petId")
                                                                     @NotNull
                                                                     @Min(value = 1)
                                                                     Long petId) {

        logger.info("getAllDiagnosis(). Retrieved all Diagnosis.");

        return ResponseEntity.ok(diagnosisService.getAllDiagnosisFromPet(petId));
    }

    @Override
    @GetMapping("/pets/{petId}/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> findDiagnosisByPetId(@PathVariable(name = "petId")
                                                             @NotNull
                                                             @Min(value = 1)
                                                             Long petId,
                                                             @PathVariable(name = "diagnosisId")
                                                             @NotNull
                                                             @Min(value = 1)
                                                             Long diagnosisId) {

        logger.info("Find Diagnosis by diagnosisId: " + diagnosisId);

        return ResponseEntity.ok(diagnosisService.findDiagnosisByPetId(petId, diagnosisId));
    }

    @Override
    @DeleteMapping("/pets/{petId}/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> deleteDiagnosisFromPet(@PathVariable("petId")
                                                               @NotNull
                                                               @Min(value = 1)
                                                               Long petId,
                                                               @PathVariable("diagnosisId")
                                                               @Min(value = 1)
                                                               Long diagnosisId) {

        diagnosisService.deleteDiagnosisFromPet(petId, diagnosisId);

        logger.info("Delete Diagnosis by diagnosisId: " + diagnosisId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/pets/{petId}/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> updateDiagnosisFromPet(@PathVariable(name = "petId")
                                                               @NotNull
                                                               @Min(value = 1)
                                                               Long petId,
                                                               @PathVariable(name = "diagnosisId")
                                                               @NotNull
                                                               @Min(value = 1)
                                                               Long diagnosisId,
                                                               @Valid
                                                               @RequestBody DiagnosisDto diagnosisDto) {

        diagnosisService.updateDiagnosisFromPet(petId, diagnosisId, diagnosisDto);

        logger.info("Updated Diagnosis with diagnosisId: " + diagnosisId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
