package io.cristos.petmanagement.controllers.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import io.cristos.petmanagement.services.diagnosis.DiagnosisService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/diagnosis")
public class DiagnosisControllerImpl implements DiagnosisController {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisControllerImpl.class);
    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisControllerImpl(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Override
    @PostMapping
    public ResponseEntity<DiagnosisDto> saveDiagnosis(@Valid @RequestBody DiagnosisDto diagnosisDto) {

        Diagnosis diagnosis = diagnosisService.saveDiagnosis(diagnosisDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{diagnosisId}")
                .buildAndExpand(diagnosis.getId())
                .toUri();

        logger.info(diagnosisDto + " saved to Database.");

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DiagnosisDto>> getAllDiagnosis() {

        logger.info("getAllDiagnosis(). Retrieved all Diagnosis.");

        return ResponseEntity.ok(diagnosisService.getAllDiagnosis());
    }

    @Override
    @GetMapping("/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> findDiagnosisById(@PathVariable Long diagnosisId) {

        logger.info("Find Diagnosis by diagnosisId: " + diagnosisId);

        return ResponseEntity.ok(diagnosisService.findDiagnosisById(diagnosisId));
    }

    @Override
    @DeleteMapping("/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> deleteDiagnosisById(@PathVariable Long diagnosisId) {

        logger.info("Delete Diagnosis by diagnosisId: " + diagnosisId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> updateDiagnosisById(@PathVariable Long diagnosisId,
                                                            @Valid
                                                            @RequestBody DiagnosisDto diagnosisDto) {
        diagnosisService.updateDiagnosis(diagnosisId, diagnosisDto);

        logger.info("Updated Diagnosis with diagnosisId: " + diagnosisId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
