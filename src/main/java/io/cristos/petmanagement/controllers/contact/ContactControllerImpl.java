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
import java.util.List;

@RestController
@RequestMapping("/v1/contacts")
@Validated
public class ContactControllerImpl implements ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactControllerImpl.class);
    private final ContactService contactService;

    @Autowired
    public ContactControllerImpl(ContactService contactService) {
        this.contactService = contactService;
    }


    @Override
    public ResponseEntity<ContactDto> findContactByVeterinarianId(@PathVariable(name = "veterinarianId")
                                                                  @Min(1)
                                                                  @NotNull
                                                                  Long veterinarianId,
                                                                  @Valid
                                                                  @RequestBody ContactDto contactDto) {

        logger.info("Find Contact by veterinarianId " + veterinarianId);

        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@Valid
                                                  @RequestBody ContactDto contactDto) {

        Contact contact = contactService.saveContact(contactDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/contactId")
                .buildAndExpand(contact.getId())
                .toUri();

        logger.info(contactDto + " Contact has been saved to the database.");

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {

        logger.info("getAllContacts(). Contacts were retrieved from the database.");

        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @Override
    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDto> findContactById(@PathVariable(name = "contactId")
                                                      @NotNull
                                                      @Min(1) Long contactId) {

        logger.info("Find contact with id: " + contactId);

        return ResponseEntity.ok(contactService.findContactById(contactId));
    }

    @Override
    @DeleteMapping("/{contactId}")
    public ResponseEntity<ContactDto> deleteContactById(@PathVariable(name = "contactId")
                                                        @NotNull
                                                        @Min(1) Long contactId) {

        contactService.deleteContactById(contactId);

        logger.info("Deleted contact with contactId: " + contactId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDto> updateContactById(@PathVariable(name = "contactId")
                                                        @NotNull
                                                        @Min(1) Long contactId,
                                                        @Valid
                                                        @RequestBody ContactDto contactDto) {
        contactService.updateContactById(contactId, contactDto);

        logger.info("Updated contact with contactId: " + contactId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
