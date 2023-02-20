package io.cristos.petmanagement.controllers.diagnosis;

import io.cristos.petmanagement.dtos.request.diagnosis.DiagnosisRequestDto;
import io.cristos.petmanagement.dtos.response.diagnosis.DiagnosisResponseDto;
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
public class DiagnosisController {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisController.class);
    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping("/diagnosis")
    public ResponseEntity<DiagnosisResponseDto> saveDiagnosis(@RequestBody
                                                              @Valid DiagnosisRequestDto diagnosisRequestDto) {

        Diagnosis diagnosis = diagnosisService.saveDiagnosis(diagnosisRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{diagnosisId}")
                .buildAndExpand(diagnosis.getId())
                .toUri();

        logger.info("Saved diagnosis " + diagnosisRequestDto);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisResponseDto> findDiagnosisById(@PathVariable(name = "diagnosisId")
                                                                  @NotNull
                                                                  @Min(1)
                                                                  Long diagnosisId) {

        logger.info("Find Diagnosis by diagnosisId: " + diagnosisId);

        return ResponseEntity.ok(diagnosisService.findDiagnosisById(diagnosisId));
    }

    @GetMapping("/diagnosis")
    public ResponseEntity<List<DiagnosisResponseDto>> getALlDiagnosis() {

        logger.info("Retrieved all diagnosis");

        return ResponseEntity.ok(diagnosisService.getAllDiagnosis());
    }

    @PutMapping("/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisResponseDto> updateDiagnosis(@PathVariable(name = "diagnosisId")
                                                                @NotNull
                                                                @Min(1)
                                                                Long diagnosisId,
                                                                @RequestBody DiagnosisRequestDto diagnosisRequestDto) {

        diagnosisService.updateDiagnosis(diagnosisId, diagnosisRequestDto);

        logger.info("Updated diagnosis with diagnosisId: " + diagnosisId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/diagnosis/{diagnosisId}")
    public ResponseEntity<DiagnosisResponseDto> deleteDiagnosisById(@PathVariable(name = "diagnosisId")
                                                                    @NotNull
                                                                    @Min(1)
                                                                    Long diagnosisId) {

        diagnosisService.deleteDiagnosisById(diagnosisId);

        logger.info("Updated diagnosis with diagnosisId: " + diagnosisId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PostMapping(value = "/pets/{petId}/diagnosis")
//    public ResponseEntity<DiagnosisResponseDto> saveDiagnosisToPet(@PathVariable(name = "petId")
//                                                           @NotNull
//                                                           @Min(value = 1)
//                                                           Long petId,
//                                                                   @Valid
//                                                           @RequestBody DiagnosisRequestDto diagnosisRequestDto) {
//
//        Diagnosis diagnosis = diagnosisService.saveDiagnosisToPet(petId, diagnosisRequestDto);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{diagnosisId}")
//                .buildAndExpand(diagnosis.getId())
//                .toUri();
//
//        logger.info(diagnosisRequestDto + " saved to Database.");
//
//        return ResponseEntity.created(location).build();
//    }
//
//    @GetMapping("/pets/{petId}/diagnosis")
//    public ResponseEntity<List<DiagnosisResponseDto>> getAllDiagnosesFromPet(@PathVariable(name = "petId")
//                                                                     @NotNull
//                                                                     @Min(value = 1)
//                                                                     Long petId) {
//
//        logger.info("getAllDiagnosis(). Retrieved all Diagnosis.");
//
//        return ResponseEntity.ok(diagnosisService.getAllDiagnosisFromPet(petId));
//    }
//
//    @GetMapping("/pets/{petId}/diagnosis/{diagnosisId}")
//    public ResponseEntity<DiagnosisResponseDto> findDiagnosisByPetId(@PathVariable(name = "petId")
//                                                             @NotNull
//                                                             @Min(value = 1)
//                                                             Long petId,
//                                                             @PathVariable(name = "diagnosisId")
//                                                             @NotNull
//                                                             @Min(value = 1)
//                                                             Long diagnosisId) {
//
//        logger.info("Find Diagnosis by diagnosisId: " + diagnosisId);
//
//        return ResponseEntity.ok(diagnosisService.findDiagnosisByPetId(petId, diagnosisId));
//    }
//
//    @DeleteMapping("/pets/{petId}/diagnosis/{diagnosisId}")
//    public ResponseEntity<DiagnosisResponseDto> deleteDiagnosisFromPet(@PathVariable("petId")
//                                                               @NotNull
//                                                               @Min(value = 1)
//                                                               Long petId,
//                                                               @PathVariable("diagnosisId")
//                                                               @Min(value = 1)
//                                                               Long diagnosisId) {
//
//        diagnosisService.deleteDiagnosisFromPet(petId, diagnosisId);
//
//        logger.info("Delete Diagnosis by diagnosisId: " + diagnosisId);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping("/pets/{petId}/diagnosis/{diagnosisId}")
//    public ResponseEntity<DiagnosisResponseDto> updateDiagnosisFromPet(@PathVariable(name = "petId")
//                                                               @NotNull
//                                                               @Min(value = 1)
//                                                               Long petId,
//                                                               @PathVariable(name = "diagnosisId")
//                                                               @NotNull
//                                                               @Min(value = 1)
//                                                               Long diagnosisId,
//                                                               @Valid
//                                                               @RequestBody DiagnosisRequestDto diagnosisRequestDto) {
//
//        diagnosisService.updateDiagnosisFromPet(petId, diagnosisId, diagnosisRequestDto);
//
//        logger.info("Updated Diagnosis with diagnosisId: " + diagnosisId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
