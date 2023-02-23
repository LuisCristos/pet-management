package io.cristos.petmanagement.controllers.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import io.cristos.petmanagement.services.contact.VeterinarianContactService;
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
@RequestMapping("/v1/veterinarians")
@Validated
public class VeterinarianContactController {

    private final Logger logger = LoggerFactory.getLogger(VeterinarianContactController.class);
    private final VeterinarianContactService veterinarianContactService;

    @Autowired
    public VeterinarianContactController(VeterinarianContactService veterinarianContactService) {
        this.veterinarianContactService = veterinarianContactService;
    }

    @PostMapping("/{veterinarianId}/contacts")
    public ResponseEntity<ContactResponseDto> saveVeterinarianContactByVeterinarianId(@PathVariable(name = "veterinarianId")
                                                                                      @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                      Long veterinarianId,
                                                                                      @Valid
                                                                                      @RequestBody ContactRequestDto contactRequestDto) {

        Veterinarian veterinarian = veterinarianContactService.saveVeterinarianContactByVeterinarianId(veterinarianId, contactRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{contactId}")
                .buildAndExpand(veterinarian.getContact().getId())
                .toUri();

        logger.info("Saved contact for veterinarian with id " + veterinarianId);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{veterinarianId}/contacts")
    public ResponseEntity<ContactResponseDto> findVeterinarianContactByVeterinarianId(@PathVariable(name = "veterinarianId")
                                                                                      @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                      Long veterinarianId) {

        logger.info("Find contact for veterinarian with id: " + veterinarianId);

        return ResponseEntity.ok(veterinarianContactService.findVeterinarianContactByVeterinarianId(veterinarianId));
    }

    @PutMapping("/{veterinarianId}/contacts/{contactId}")
    public ResponseEntity<ContactResponseDto> updateVeterinarianContactByVeterinarianId(@PathVariable(name = "veterinarianId")
                                                                                        @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                        Long veterinarianId,
                                                                                        @Valid
                                                                                        @RequestBody ContactRequestDto contactRequestDto,
                                                                                        @PathVariable(name = "contactId")
                                                                                        @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                        Long contactId) {

        logger.info("Updated contact for veterinarian with id " + veterinarianId);

        veterinarianContactService.updateVeterinarianContactByVeterinarianId(veterinarianId, contactRequestDto, contactId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{veterinarianId}/contacts/{contactId}")
    public ResponseEntity<ContactResponseDto> deleteVeterinarianContactByVeterinarianId(@PathVariable(name = "veterinarianId")
                                                                                        @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                        Long veterinarianId,
                                                                                        @PathVariable(name = "contactId")
                                                                                        @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                        Long contactId) {

        logger.info("Deleted contact for veterinarian with id " + veterinarianId);

        veterinarianContactService.deleteVeterinarianContactByVeterinarianId(veterinarianId, contactId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
