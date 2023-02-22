package io.cristos.petmanagement.controllers.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.services.contact.ContactService;
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
import java.util.List;

@RestController
@RequestMapping("/v1/contacts")
@Validated
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Contact
    @PostMapping
    public ResponseEntity<ContactResponseDto> saveContact(@RequestBody
                                                          @Valid
                                                          ContactRequestDto contactRequestDto) {

        Contact contact = contactService.saveContact(contactRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/contactId")
                .buildAndExpand(contact.getId())
                .toUri();

        logger.info("Saved contact to database.");

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactResponseDto> findContactById(@PathVariable(name = "contactId")
                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
                                                              Long contactId) {

        logger.info("Find contact with id: " + contactId);

        return ResponseEntity.ok(contactService.findContactById(contactId));
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getAllContacts() {

        logger.info("Retrieved all contacts.");

        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactResponseDto> updateContactById(@PathVariable(name = "contactId")
                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                Long contactId,
                                                                @RequestBody
                                                                @Valid
                                                                ContactRequestDto contactRequestDto) {

        contactService.updateContactById(contactId, contactRequestDto);

        logger.info("Updated contact with id " + contactId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<ContactResponseDto> deleteContactById(@PathVariable(name = "contactId")
                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                Long contactId) {

        contactService.deleteContactById(contactId);

        logger.info("Deleted contact with id: " + contactId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
