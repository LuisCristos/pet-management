package io.cristos.petmanagement.controllers.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.services.contact.ContactService;
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

@RestController
@RequestMapping("/v1")
@Validated
public class ContactControllerImpl implements ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactControllerImpl.class);
    private final ContactService contactService;

    @Autowired
    public ContactControllerImpl(ContactService contactService) {
        this.contactService = contactService;
    }


    @Override
    @GetMapping("/veterinarians/{veterinarianId}/contacts/{contactId}")
    public ResponseEntity<ContactDto> findContactByVeterinarianId(@PathVariable(name = "veterinarianId")
                                                                  @Min(1)
                                                                  @NotNull
                                                                  Long veterinarianId,
                                                                  @PathVariable(name = "contactId")
                                                                  @Min(1)
                                                                  @NotNull
                                                                  Long contactId) {

        logger.info("Find contact for veterinarian with id: " + veterinarianId);

        return ResponseEntity.ok(contactService.findContactByVeterinarianId(veterinarianId, contactId));
    }

    @Override
    @PostMapping("/veterinarians/{veterinarianId}/contacts")
    public ResponseEntity<ContactDto> saveContactToVeterinarianByID(@PathVariable(name = "veterinarianId")
                                                                    @Min(1)
                                                                    @NotNull
                                                                    Long veterinarianId,
                                                                    @Valid
                                                                    @RequestBody ContactDto contactDto) {

        Contact contact = contactService.saveContactToVeterinarianByID(veterinarianId, contactDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{contactId}")
                .buildAndExpand(contact.getId())
                .toUri();

        logger.info("Saved contact for veterinarian with id " + veterinarianId + " contact " + contactDto);

        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("/veterinarians/{veterinarianId}/contacts/{contactId}")
    public ResponseEntity<ContactDto> updateContactToVeterinarianById(@PathVariable(name = "veterinarianId")
                                                                      @Min(1)
                                                                      @NotNull
                                                                      Long veterinarianId,
                                                                      @Valid
                                                                      @RequestBody ContactDto contactDto,
                                                                      @PathVariable(name = "contactId")
                                                                      @Min(1)
                                                                      @NotNull
                                                                      Long contactId) {

        contactService.updateContactToVeterinarianById(veterinarianId, contactDto, contactId);

        logger.info("Updated contact with contactId: " + contactId + " for veterinarian with id: " + veterinarianId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/veterinarians/{veterinarianId}/contacts/{contactId}")
    public ResponseEntity<ContactDto> deleteContactToVeterinarianById(@PathVariable(name = "veterinarianId")
                                                                      @Min(1)
                                                                      @NotNull
                                                                      Long veterinarianId,
                                                                      @PathVariable(name = "contactId")
                                                                      @Min(1)
                                                                      @NotNull
                                                                      Long contactId) {


        contactService.deleteContactToVeterinarianById(veterinarianId, contactId);

        logger.info("Deleted contact with contactId: " + contactId + " for veterinarian with id: " + veterinarianId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
