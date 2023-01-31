package io.cristos.petmanagement.controllers.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import io.cristos.petmanagement.services.diagnosis.DiagnosisService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1")
@Validated
public class DiagnosisControllerImpl implements DiagnosisController {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisControllerImpl.class);
    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisControllerImpl(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Override
    @PostMapping(value = "/pets/{petId}/diagnosis")
    public ResponseEntity<DiagnosisDto> saveDiagnosisToPet(@PathVariable(name = "petId")
                                                           @Min(1) Long petId,
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
    public ResponseEntity<PetDto> getAllDiagnosis(@PathVariable(name = "petId")
                                                  @Min(1) Long petId) {

        logger.info("getAllDiagnosis(). Retrieved all Diagnosis.");

        return ResponseEntity.ok(diagnosisService.getAllDiagnosis(petId));
    }

    @Override
    @GetMapping("/pets/{petId}/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> findDiagnosisById(@PathVariable(name = "petId")
                                                          @Min(1) Long petId,
                                                          @PathVariable(name = "diagnosisId")
                                                          @Min(1) Long diagnosisId) {

        logger.info("Find Diagnosis by diagnosisId: " + diagnosisId);

        return ResponseEntity.ok(diagnosisService.findDiagnosisById(petId, diagnosisId));
    }

    @Override
    @DeleteMapping("/pets/{petId}/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> deleteDiagnosisById(@PathVariable("petId")
                                                            @Min(1) Long petId,
                                                            @PathVariable("diagnosisId")
                                                            @Min(1) Long diagnosisId) {

        diagnosisService.deleteDiagnosis(petId, diagnosisId);

        logger.info("Delete Diagnosis by diagnosisId: " + diagnosisId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/pets/{petId}/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> updateDiagnosisById(@PathVariable(name = "petId")
                                                            @Min(1) Long petId,
                                                            @PathVariable(name = "diagnosisId")
                                                            @Min(1) Long diagnosisId,
                                                            @Valid
                                                            @RequestBody DiagnosisDto diagnosisDto) {

        diagnosisService.updateDiagnosis(petId, diagnosisId, diagnosisDto);

        logger.info("Updated Diagnosis with diagnosisId: " + diagnosisId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
